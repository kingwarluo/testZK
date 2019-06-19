package com.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketChannelDemo {

    public static void main(String[] args) {
        ServerSocketChannelDemo sscd = new ServerSocketChannelDemo();
        try {
            sscd.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();
    public static Map<Socket, Long> time_stat = new HashMap<Socket, Long>();

    private void startServer() throws IOException {
        selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8000));
        ssc.configureBlocking(false);//非阻塞

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        int count = 0;
        while((count = selector.select()) > 0){
            //调用阻塞的select,等待 selector上注册的事件发生
            Set readyKeys = selector.selectedKeys();
            //获取就绪事件
            Iterator i = readyKeys.iterator();
            long e = 0;
            while(i.hasNext()){
                SelectionKey sk = (SelectionKey) i.next();
                //先移除该事件,避免重复通知
                i.remove();
                // 新连接
                if(sk.isAcceptable()){
                    doAccept(sk);
                // 服务端关心的可读，意味着有数据从client传来了数据
                }else if(sk.isValid() && sk.isReadable()){
                    if(time_stat.containsKey(((SocketChannel)sk.channel()).socket())){
                        time_stat.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
                    }
                    doRead(sk);
                } else if(sk.isValid() && sk.isWritable()){
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend:" + (e-b) + "ms");
                }
            }
        }
    }

    public void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;
        try {
            // 新注册channel
            clientChannel = server.accept();
            // 非阻塞模式
            clientChannel.configureBlocking(false);
            // 注册读事件（服务端一般不注册 可写事件）
            SelectionKey clientKey = server.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from " + clientAddress.getHostAddress() + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;
        try {
            len = channel.read(bb);
            if(len < 0){
                disconnect(sk);
                return;
            }
        } catch (IOException e) {
            System.out.println("failed to read from client");
            e.printStackTrace();
            disconnect(sk);
            return;
        }
        bb.flip();
        tp.execute(new HandleMsg(sk, bb));
    }

    public void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient) sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();
        try {
            int len = channel.write(bb);
            if (len == -1){
                disconnect(sk);
                return;
            }
            if(bb.remaining() == 0){
                outq.removeLast();
            }
        } catch (IOException e) {
            e.printStackTrace();
            disconnect(sk);
        }
        if(outq.size() == 0){
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    private void disconnect(SelectionKey sk) {
        if(sk != null){
            sk.cancel();
        }
    }

    class EchoClient {

        private LinkedList<ByteBuffer> outq;

        EchoClient(){
            outq = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue(){
            return outq;
        }

        public void enqueue(ByteBuffer bb){
            outq.addFirst(bb);
        }

    }

    class HandleMsg implements Runnable {

        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb){
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            //强迫selector立即返回
            selector.wakeup();
        }

    }

}

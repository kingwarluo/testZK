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

public class SocketChannelDemo {

    public static void main(String[] args) {
        SocketChannelDemo scd = new SocketChannelDemo();
        try {
            scd.init("127.0.0.1", 8000);
            scd.working();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Selector selector;

    public void init(String ip, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = Selector.open();
        channel.connect(new InetSocketAddress(ip, port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    public void working() throws IOException {
        while(true){
            if(!selector.isOpen()){
                break;
            }
            selector.select();
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();
                if(key.isConnectable()){
                    connect(key);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    public void connect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if(channel.isConnectionPending()){
            channel.finishConnect();
        }
        channel.configureBlocking(false);
        channel.write(ByteBuffer.wrap(new String("Hello server!").getBytes()));
        channel.register(selector, SelectionKey.OP_READ);
    }

    public void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer bb = ByteBuffer.allocate(100);
        channel.read(bb);
        byte[] data = bb.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到消息:" + msg);
        channel.close();
        key.selector().close();
    }

}

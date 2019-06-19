package com.nio;

import io.netty.channel.ServerChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kingwarluo
 * NIO服务端
 * BIO、NIO、AIO
 * @date 2019/1/29 11:19
 */
public class NIOEchoServer {

    public static void main(String[] args) {
        try {
            // 1.新建线程池
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            // 2.新建ServerSocketChannel
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            // 3.设置为非阻塞模式
            serverChannel.configureBlocking(false);
            // 4.注册selector
            Selector selector = Selector.open();
            // 5.将serverChannel注册到selector中
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // 连接时处理
            // 6.serverChannel绑定端口
            serverChannel.bind(new InetSocketAddress(9999));
            System.out.println("服务端启动成功，绑定端口：9999");

            // 7.持续监听连接
            int keySelect = 0; //接收轮询状态
            while((keySelect = selector.select()) > 0){// 实现轮询处理
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();//获取key信息
                    if (selectionKey.isAcceptable()) {// 为连接模式
                        SocketChannel clientChannel = serverChannel.accept();// 等待连接
                        if (clientChannel != null) {
                            executorService.submit(new EchoClientHandler(clientChannel));// 执行客户端处理
                        }
                    }
                    //移除
                    iterator.remove();
                }
            }
            executorService.shutdown();
            serverChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class EchoClientHandler implements Runnable {
        private SocketChannel clientChannel;
        private boolean flag = true;
        public EchoClientHandler(SocketChannel clientChannel){
            this.clientChannel = clientChannel;
        }
        @Override
        public void run() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(50);
            try{
                while(flag) {
                    byteBuffer.clear();
                    // 1.读取数据
                    int readCount = this.clientChannel.read(byteBuffer);
                    // 2.转换消息
                    String readMessage = new String(byteBuffer.array(), 0, readCount).trim();
                    // 3.输出消息
                    String writeMessage = "[ECHO]" + readMessage + "\n"; //回应数据信息
                    if("byebye".equalsIgnoreCase(readMessage)){
                        writeMessage = "[ECHO]拜拜，下次再见";
                        this.flag = false;
                    }
                    // 输入输出都是通过缓存操作完成
                    byteBuffer.clear();
                    byteBuffer.put(writeMessage.getBytes());
                    byteBuffer.flip();
                    this.clientChannel.write(byteBuffer);// 回应数据
                }
                this.clientChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

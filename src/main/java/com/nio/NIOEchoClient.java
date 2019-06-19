package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author kingwarluo
 * NIO客户端
 * @date 2019/1/29 11:42
 */
public class NIOEchoClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // 1.创建连接
        SocketChannel clientChannel = SocketChannel.open();
        // 2.连接服务端
        clientChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        // 3.开辟缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(50);
        boolean flag = true;
        while (flag) {
            byteBuffer.clear();
            String inputData = scanner.next();
            byteBuffer.put(inputData.getBytes());
            byteBuffer.flip();
            clientChannel.write(byteBuffer);
            byteBuffer.clear();//读取前先清空
            int readCount = clientChannel.read(byteBuffer);
            byteBuffer.flip();
            String read = new String(byteBuffer.array(), 0, readCount);
            System.out.println(read);
            if("byebye".equalsIgnoreCase(inputData)){
                flag = false;
            }
        }
        clientChannel.close();
    }

}

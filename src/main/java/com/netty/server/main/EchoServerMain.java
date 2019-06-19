package com.netty.server.main;

import com.netty.server.EchoServer;

/**
 * @author kingwarluo
 * netty执行类
 * @date 2019/1/29 15:45
 */
public class EchoServerMain {

    public static void main(String[] args) throws InterruptedException {
        EchoServer server = new EchoServer();
        server.start();
        /**
         * 打印系统参数列表
         */
        System.getProperties().list(System.out);
    }

}

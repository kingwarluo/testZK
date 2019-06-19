package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static BufferedReader br = null;
    private static PrintWriter pw = null;
    private static ServerSocket ss;
    private static Socket s;
    static Scanner scanner = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            ss = new ServerSocket(5500);
            System.out.println("服务器正常启动。。。。");
            while(true){
                s = ss.accept();//阻塞方法
                System.out.println("连接成功" + s.getRemoteSocketAddress());
                new SocketThread(s).start();
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            pw.close();
            br.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
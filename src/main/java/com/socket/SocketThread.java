package com.socket;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread {

    private Socket socket;

    SocketThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(socket.getRemoteSocketAddress() + "说：" + br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

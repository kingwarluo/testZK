package com.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ByteBufferDemo {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocateDirect(10);
        byte[] temp = {3,5,7,9};
        buf.put(temp);
        buf.flip();
        System.out.println("缓冲区中的内容：");
        while(buf.hasRemaining()){
            int x = buf.get();
            System.out.println(x);
        }
    }

}

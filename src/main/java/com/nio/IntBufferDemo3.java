package com.nio;

import java.nio.IntBuffer;

public class IntBufferDemo3 {

    public static void main(String[] args) {
        IntBuffer buf = IntBuffer.allocate(10);
        IntBuffer read = null;
        for (int i = 0; i < 10; i++) {
            buf.put(2 * i + 1);
        }
        read = buf.asReadOnlyBuffer();
        read.flip();
        System.out.println("缓冲区中的内容：");
        while(read.hasRemaining()){
            int x = read.get();
            System.out.println(x);
        }
        System.out.println();
        read.put(30);
    }

}

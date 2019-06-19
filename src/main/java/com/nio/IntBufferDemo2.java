package com.nio;

import java.nio.IntBuffer;

public class IntBufferDemo2 {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        IntBuffer sub = null;
        for (int i = 0; i < 10; i++) {
            intBuffer.put(2 * i + 1);
        }
        intBuffer.position(2);
        intBuffer.limit(6);
        sub = intBuffer.slice();
        for (int i = 0; i < sub.capacity(); i++) {
            int temp = sub.get(i);
            sub.put(temp - 1);
        }
        intBuffer.flip();
        intBuffer.limit(intBuffer.capacity());
        while(intBuffer.hasRemaining()){
            int x = intBuffer.get();
            System.out.println(x);
        }
    }

}

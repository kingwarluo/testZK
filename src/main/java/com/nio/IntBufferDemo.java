package com.nio;

import java.nio.IntBuffer;

public class IntBufferDemo {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("position:" + intBuffer.position() + ",limit:" + intBuffer.limit() + ", capacity:" + intBuffer.capacity());
        int[] temp = {5,7,9};
        intBuffer.put(3);
        intBuffer.put(temp);
        System.out.println("写入数据之后的position、limit和capacity：") ;
        System.out.println("position:" + intBuffer.position() + ",limit:" + intBuffer.limit() + ", capacity:" + intBuffer.capacity());
        intBuffer.flip();// 重置缓冲区
        System.out.println("准备输出数据时的position、limit和capacity：") ;
        System.out.println("position = " + intBuffer.position() + "，limit = "
                + intBuffer.limit() + "，capacity = " + intBuffer.capacity());
        while (intBuffer.hasRemaining()){
            int x = intBuffer.get();
            System.out.println(x);
        }
        System.out.println("get之后的position、limit和capacity：") ;
        System.out.println("position = " + intBuffer.position() + "，limit = "
                + intBuffer.limit() + "，capacity = " + intBuffer.capacity());
        intBuffer.clear();
        intBuffer.put(22);
        System.out.println("clear后的position、limit和capacity：") ;
        System.out.println("position = " + intBuffer.position() + "，limit = "
                + intBuffer.limit() + "，capacity = " + intBuffer.capacity());
    }

}

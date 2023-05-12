package com.netty.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author kingwarluo
 * @{description}
 * @date 2023/5/12 10:31
 */
public class BufferLearn {

    public static void main(String[] args) {
        // 分配空间
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 分配直接内存，效率更快
        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16);
        // ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes());
        // ByteBuffer buffer3 = StandardCharsets.UTF_8.encode("hello"); // 直接读

        bufferOperate(buffer);


    }

    /**
     * buffer操作，flip 读模式 compact 保存未读继续写 clear 写模式 mark 标记 reset 回到标记
     * @param buffer
     */
    private static void bufferOperate(ByteBuffer buffer) {
        buffer.put("kingwarluo".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        System.out.println("-------------------------");
        buffer.clear();
        buffer.put("luojianhua".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        System.out.println("-------------------------");
        // rewind重置position
        buffer.rewind();
        System.out.println(buffer.get());
        buffer.mark();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println("-------------------------");
        buffer.reset();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        System.out.println("------------字符 bytebuffer互转-------------");
        // 字符 bytebuffer互转
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("华哥"); // 直接读
        while (buffer1.hasRemaining()) {
            System.out.println(buffer1.get());
        }
        buffer1.rewind();
        System.out.println(StandardCharsets.UTF_8.decode(buffer1).toString());
    }

}

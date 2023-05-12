package com.netty.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 粘包拆包半包
 * @author kingwarluo
 * @date 2023/5/12 10:53
 */
public class BufferSticky {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        splitSource(buffer);
        buffer.put("w are you\n".getBytes());
        splitSource(buffer);
    }

    private static void splitSource(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if(source.get(i) == '\n') {
                int capacity = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(capacity);

                for (int j = 0; j < capacity; j++) {
                    target.put(source.get());
                }
                target.rewind();
                System.out.println(StandardCharsets.UTF_8.decode(target).toString());
                System.out.println("-------------");
            }
        }
        source.compact();
    }

}

package com.netty.buffer;

import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 分散度 集中写
 * @author kingwarluo
 * @date 2023/5/12 10:53
 */
public class BufferGather {

    public static void main(String[] args) {
        // 分散读
        try (RandomAccessFile file = new RandomAccessFile(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "netty/data.txt"), "r")) {
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(5);
            file.getChannel().read(new ByteBuffer[]{b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();
            while (b1.hasRemaining()) {
                System.out.println(b1.get());
            }
            b1.rewind();
            System.out.println(StandardCharsets.UTF_8.decode(b1).toString());
            System.out.println("----------------------");
            while (b2.hasRemaining()) {
                System.out.println(b2.get());
            }
            b2.rewind();
            System.out.println(StandardCharsets.UTF_8.decode(b2).toString());
            System.out.println("----------------------");
            while (b3.hasRemaining()) {
                System.out.println(b3.get());
            }
            b3.rewind();
            System.out.println(StandardCharsets.UTF_8.decode(b3).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 集中写
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好");
//        try (RandomAccessFile file = new RandomAccessFile(new File(ResourceUtils.CLASSPATH_URL_PREFIX + "netty/gather.txt"), "rw")) {
        try (RandomAccessFile file = new RandomAccessFile("gather.txt", "rw")) {
            file.getChannel().write(new ByteBuffer[]{b1, b2, b3});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        String info[] = {"MLDN", "MLDNJAVA", "www.baidu.com",
                "www.sina.com", "通道", "channel"};
        File file = new File("D:" + File.separator + "put.txt");
        FileOutputStream fos = null;
        fos = new FileOutputStream(file);
        FileChannel fc = null;
        fc = fos.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        for (int i = 0; i < info.length; i++) {
            buf.put(info[i].getBytes());
        }
        buf.flip();
        fc.write(buf);
        fc.close();
        fos.close();
    }

}

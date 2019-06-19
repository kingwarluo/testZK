package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo2 {

    public static void main(String[] args) throws IOException {
        File take = new File("D:" + File.separator + "take.txt");
        File put = new File("D:" + File.separator + "put.txt");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        fis = new FileInputStream(take);
        fos = new FileOutputStream(put);
        FileChannel ftake = null;
        FileChannel fput = null;
        ftake = fis.getChannel();
        fput = fos.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int temp = ftake.read(buf);
        while((temp = ftake.read(buf)) != -1){
            buf.flip();
            fput.write(buf);
            buf.clear();
        }
        ftake.close();
        fput.close();
        fis.close();
        fos.close();
    }

}

package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo3 {

    public static void main(String[] args) throws IOException {
        File file = new File("d:" + File.separator + "kingwarluo.txt");
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        FileChannel fin = null;
        fin = fis.getChannel();
        MappedByteBuffer mbb = null;
        mbb = fin.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        byte data[] = new byte[(int) file.length()];
        int foot = 0;
        while(mbb.hasRemaining()){
            data[foot++] = mbb.get();
        }
        System.out.println(new String(data));
        fin.close();
        fis.close();
    }

}

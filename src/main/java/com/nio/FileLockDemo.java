package com.nio;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("D:" + File.separator + "kingwarluo.txt");
        FileOutputStream fis = new FileOutputStream(file);
        FileChannel fin = fis.getChannel();
        FileLock fl = fin.tryLock();
        if(fl != null){
            System.out.println("文件锁定15秒");
            Thread.sleep(15 * 1000);
            fl.release();
            System.out.println("文件锁解除");
        }
        fin.close();
        fis.close();

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.getChannel();
    }

}

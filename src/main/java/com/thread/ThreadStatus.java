package com.thread;

import java.util.concurrent.TimeUnit;

public class ThreadStatus {

    private static int count = 0;

    public static void main(String[] args) {
        // 验证线程状态  jps查看当前线程pid， jstack pid查看线程状态
//        new Thread(new WaitingThread(), "WaitingThread").start();
//        new Thread(new TimedWaitingThread(), "TimedWaitingThread").start();
//        new Thread(new BlockedThread(), "BlockedThread-1").start();
//        new Thread(new BlockedThread(), "BlockedThread-2").start();

        // 线程安全性问题：可见性、原子性、有序性
        // 实际是小于1000的，因为count++是分三条指令执行，没有原子性
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runner()).start();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    static class Runner implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }


    static class WaitingThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (WaitingThread.class) {
                    try {
                        WaitingThread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TimedWaitingThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BlockedThread implements Runnable {

        @Override
        public void run() {
            synchronized (BlockedThread.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

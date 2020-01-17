package com.time;

import java.util.concurrent.CountDownLatch;

public class Time {

    public static void main(String[] args) throws InterruptedException {
        int count = 100;
        /**并发*/
        long interval = concurrentTest(count, ()->{System.nanoTime();});
        System.out.format("[%s] thread concurrent test <nanoTime> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

        /**串行循环*/
        interval = serialNanoTime(count);
        System.out.format("[%s] count serial test <nanoTime> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");

        /**并发*/
        interval = concurrentTest(count, ()->{System.currentTimeMillis();});
        System.out.format("[%s] thread concurrent test <currentTimeMillis> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

        /**串行循环*/
        interval = serialCurrentTime(count);
        System.out.format("[%s] count serial test <currentTimeMillis> cost total time [%s]ns, average time [%s]ns.\n", count, interval, interval/count);

    }

    private static long concurrentTest(int threads, final Runnable r) throws InterruptedException {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                try {
                    start.await();
                    try {
                        r.run();
                    }finally {
                        end.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println(System.nanoTime());
        long stime = System.nanoTime();
        start.countDown();
        end.await();
        return System.nanoTime() - stime;
    }

    private static long serialNanoTime(int count){
        long stime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            System.nanoTime();
        }
        return System.nanoTime() - stime;
    }

    private static long serialCurrentTime(int count){
        long stime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            System.currentTimeMillis();
        }
        return System.nanoTime() - stime;
    }

}

package com.semaphore;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/6/10 9:51
 */
public class SemaphoreTest {

    // 同一时间只能5个线程通过
    static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        // 等待100个做完后，继续执行
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            new Thread(new CountDownLatchRun(countDownLatch, "计数" + i)).start();
        }

        try {
            countDownLatch.await();
            System.out.println("【计数100完成】");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 等待100个线程都到齐再执行后续
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100, new BarrierRun());

        for (int i = 0; i < 100; i++) {
            new Thread(new SemaphoreRun(cyclicBarrier, "信号量" + i)).start();
        }
    }

    static class SemaphoreRun implements Runnable {

        private String name;

        private CyclicBarrier cyclicBarrier;

        public SemaphoreRun(CyclicBarrier cyclicBarrier, String name) {
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(String.format("当前等待%s人", cyclicBarrier.getNumberWaiting()));
                // 表示有一个到达，并且为满足人数，开始等待
                cyclicBarrier.await();

                semaphore.acquire();
                int sleep = new Random().nextInt(10) * 100;
                Thread.sleep(sleep);
                System.out.println(String.format("%s休眠了%s毫秒后，执行完成, 队列还是有%s人", name, sleep, semaphore.getQueueLength()));
                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class BarrierRun implements Runnable {

        @Override
        public void run() {
            System.out.println("【100个士兵到齐】");
        }
    }


    static class CountDownLatchRun implements Runnable {

        private CountDownLatch countDownLatch;

        private String name;

        public CountDownLatchRun(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                int sleep = new Random().nextInt(10) * 100;
                Thread.sleep(sleep);
                System.out.println(String.format("%s休眠%s毫秒后完成", name, sleep));
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {

    /**
     * 最大线程池大小
     */
    public static final int CORE_POOL_SIZE = 2;

    /**
     * 最大线程池大小
     */
    public static final int MAX_POOL_SIZE = 10;

    /**
     * 阻塞队列容量
     */
    public static final int CAPACITY = 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = new ThreadPoolExecutorExtend(CORE_POOL_SIZE, MAX_POOL_SIZE, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        Share share = new Share();
        List<Future> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Future future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行中");
                    share.addA();
                }
            });
            res.add(future);
        }
        for (Future fu : res) {
            fu.get();
        }
        System.out.println("share.a=" + share.getA());
        executor.shutdown();

    }

}

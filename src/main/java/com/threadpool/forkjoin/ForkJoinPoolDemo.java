package com.threadpool.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/4/25 10:07
 */
@Slf4j
public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        int n = 20;
        // 为了追踪子线程名称，需要重写ForkJoinWorkerThreadFactory的方法
        final ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
            final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            worker.setName("my-thread" + worker.getPoolIndex());
            return worker;
        };
        // 创建分治任务线程池，可以追踪到线程名称
        ForkJoinPool forkJoinPool = new ForkJoinPool(4, factory, null, false);
        // 快速创建 ForkJoinPool 方法
        // ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        // 创建分治任务
        Fibonacci fibonacci = new Fibonacci(n);

        // 调用invoke方法启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);
        log.info("Fibonacci {} 的结果是 {}", n, result);
    }

}

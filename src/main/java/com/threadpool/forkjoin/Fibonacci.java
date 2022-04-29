package com.threadpool.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/4/25 10:19
 */
@Slf4j
public class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n <= 1) {
            return n;
        }
        log.info(Thread.currentThread().getName());
        Fibonacci f1 = new Fibonacci(n -1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}

package com.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 手动实现超时
 *
 * @author jianhua.luo
 * @date 2020/12/7
 */
public class TimeOutImplement {

    @SneakyThrows
    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            int n = 4;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                Thread.sleep(1000);
                sum += i;
            }
            return sum;
        };
        int timeout = 2000;
        Integer timeoutValue = -1;
        TimeOutCallable<Integer> timeoutCallable = new TimeOutCallable<>(callable, timeout, timeoutValue);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(timeoutCallable);
        Integer result = future.get();
        executor.shutdown();

        if (timeoutValue.equals(result)) {
            System.out.println("--任务超时--");
        } else {
            System.out.println("任务结果：" + result);
        }
    }

    /**
     * 超时线程
     * callable：需要执行的业务逻辑
     * timeoutV：超时的返回值
     * timeout：超时时间
     * 原理：启动一个单线程池，利用future.get(timeout, unit)单位来实现
     *
     * @author jianhua.luo
     * @date 2020/12/7
     */
    static class TimeOutCallable<V> implements Callable<V> {

        private final Callable<V> callable;

        private final V timeoutV;

        private final long timeout;

        public TimeOutCallable(Callable<V> callable, long timeout, V timeoutV) {
            this.callable = callable;
            this.timeoutV = timeoutV;
            this.timeout = timeout;
        }

        @Override
        public V call() throws Exception {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<V> future = executorService.submit(callable);
            V v = null;
            try {
                v = future.get(timeout, TimeUnit.MICROSECONDS);
            }catch (TimeoutException timeoutEx) {
                System.out.println("callable 超时");
            }
            executorService.shutdownNow(); // 给线程池中所有正在运行的线程发送 中断 信号
            return v != null ? v : timeoutV;
        }
    }

}

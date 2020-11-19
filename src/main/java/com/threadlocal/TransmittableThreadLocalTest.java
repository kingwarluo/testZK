package com.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 阿里 跨线程池传递线程变量 库
 *
 * @author jianhua.luo
 * @date 2020/11/19
 */
public class TransmittableThreadLocalTest {

    public static ThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<>();
    public static ExecutorService executorService =
            TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开启");
        threadLocal.set(1);
        System.out.println("主线程读取本地变量：" + threadLocal.get());

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量：" + threadLocal.get());
        });

        TimeUnit.SECONDS.sleep(1);

        threadLocal.set(2);
        System.out.println("主线程读取本地变量：" + threadLocal.get());

        executorService.submit(() -> {
            //[读到了主线程修改后的新值]
            System.out.println("子线程读取本地变量：" + threadLocal.get());
            threadLocal.set(3);
            System.out.println("子线程读取本地变量：" + threadLocal.get());
        });

        TimeUnit.SECONDS.sleep(1);
        //依旧读取的是 2
        System.out.println("主线程读取本地变量：" + threadLocal.get());
    }

}

package com.threadpool;

import java.util.concurrent.*;

/**
 * @description:线程池扩展类
 *
 * @author jianhua.luo
 * @date 2019/6/10
 */
public class ThreadPoolExecutorExtend extends ThreadPoolExecutor {

    public ThreadPoolExecutorExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(t);
        System.out.println(r);
        System.out.println("执行前。。");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println(r);
        System.out.println("执行后。。。");
    }

}

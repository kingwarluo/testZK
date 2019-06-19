package com.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description 线程池拒绝策略
 * 
 * @author jianhua.luo
 * @version 1.0
 * @date 2019/4/4 16:27
 */
public class ThreadPoolRejectPolicy {

    /**
     * 最大线程池大小
     */
    public static final int CORE_POOL_SIZE = 1;

    /**
     * 最大线程池大小
     */
    public static final int MAX_POOL_SIZE = 1;

    /**
     * 阻塞队列容量
     */
    public static final int CAPACITY = 1;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY));
        /**
         * 设置线程池拒绝策略
         * 1、AbortPolicy    超过阻塞队列直接抛出异常RejectedExecutionException
         * 2、CallerRunsPolicy   数组越界后，用启动线程池线程本身执行
         * 3、DiscardOldestPolicy    数组越界清除最老的
         * 4、DiscardPolicy  数组越界直接拒绝
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i ++){
            executor.execute(new MyRunner(i));
            Thread.sleep(100);
        }
        executor.shutdown();

    }

    public class MyRejectPolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                r.run();
            }
        }
    }

    public static class MyRunner implements Runnable {

        private int num;

        public MyRunner(int num){
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + num);
        }
    }

    /**
     * @description 二进制测试
     * 
     * @author jianhua.luo
     * @version 1.0
     * @date 2019/4/4 17:01
     */
    public static void binaryTest(){
        int i = (1 << 29) - 1;
        System.out.println(Integer.toBinaryString(i));

        /**
         * 1的32位位移
         * 1、获取补码，转换成二进制，就是最后的结果
         * 0000 0000 0000 0000 0000 0000 0000 0001
         * -1的32位位移
         * 1、获取补码，转换成二进制（负数首位为1）
         * 1000 0000 0000 0000 0000 0000 0000 0001
         * 2、对补码取反，加1，就是最后的二进制结果
         * 1111 1111 1111 1111 1111 1111 1111 1111
         */
        int i2 = -1 << 32;
        System.out.println(Integer.toBinaryString(i2));

        /**
         * 异或运算为负数时，再取一次补码
         * 首位1不变，其余位取反+1
         */

    }

}

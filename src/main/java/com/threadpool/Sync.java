package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description 测试同步块的执行
 * 
 * @author jianhua.luo
 * @version 1.0
 * @date 2019/4/11 17:50
 */
public class Sync {

    public static void main(String[] args) {

//        ExecutorService executor = Executors.newFixedThreadPool(10);


        Runnable r = new Runnable() {
            @Override
            public void run() {
                Bank bank = new Bank();
                try {
                    bank.setMoney(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bank.clearMoney();
            }
        };

        Thread t = new Thread(r);
        t.setName("t_");
        Thread t2 = new Thread(r);
        t2.setName("t2_");

        t.start();
        t2.start();

//        for (int i = 0; i < 10; i++) {
//            executor.submit(t);
//        }
//        executor.shutdown();
    }

    public static class Bank {

        private Object LOCK = new Object();

        private int money = 1;

        public void setMoney(int m) throws InterruptedException {
            synchronized (LOCK) {
                for (int i = 0; i < 10; i++) {
                    money += m;
                    System.out.println(Thread.currentThread().getName() + money);
                    Thread.sleep(100);
                }
            }
        }

        public void clearMoney(){
            synchronized (LOCK) {
                money = 0;
                System.out.println(Thread.currentThread().getName() + "clear");
            }
        }

    }

}

package com.threadlocal;

/**
 * @description 当前线程测试类
 * 
 * @author jianhua.luo
 * @version 1.0
 * @date 2019/4/23 14:58
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Transfer(new Bank()));
        Thread t2 = new Thread(new Transfer(new Bank()));
        t.start();
        t2.start();
    }

    static class Transfer implements Runnable {

        Bank bank;

        Transfer(Bank bank){
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                bank.set();
            }
            System.out.println(Thread.currentThread().getName() + " " + bank.get());
        }

    }

    static class Bank {

        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){

            @Override
            protected Integer initialValue(){
                return 100;
            }
        };

        public void set(){
            threadLocal.set(threadLocal.get() + 10);
        }

        public Integer get(){
            return threadLocal.get();
        }
    }

}

package com.threadpool;

/**
 * @description:测试线程变量可见性
 *
 * @author jianhua.luo
 * @date 2019/7/9
 */
public class ThreadVisiable {

    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();
        Thread thread = new Thread(t);
        thread.start();
        Thread.sleep(3000);
        Test.a = 2;
        while (true){
            System.out.println("主线程正在执行");
        }
    }


    public static class Test implements Runnable {

        private static int a = 1;

        @Override
        public void run() {
            while (true) {
                if(a == 2) {
                    break;
                }
                System.out.println("线程正在执行");
            }
        }

    }

}

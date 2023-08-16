package com.volatile1;

import java.util.concurrent.TimeUnit;

/**
 * 参考：https://blog.csdn.net/m0_49183244/article/details/125493673
 * @author kingwarluo
 * @date 2023/8/16 9:25
 */
public class VolatileTest {

    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    myNumber.addPlusPlus();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myNumber.number);
    }

}

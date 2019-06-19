package com.zookeeper.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-09-10 15:03
 */
public class ShaCheCheck extends DangerCenter {

    public ShaCheCheck(CountDownLatch countDownLatch){
        super("刹车", countDownLatch);
    }

    @Override
    public void check() {
        System.out.println("正在检查[" + this.getStation() + "]...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("检查[" + this.getStation() + "]完毕，可以发车。");
    }
}

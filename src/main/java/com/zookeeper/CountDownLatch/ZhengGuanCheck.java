package com.zookeeper.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-09-10 15:03
 */
public class ZhengGuanCheck extends DangerCenter {

    public ZhengGuanCheck(CountDownLatch countDownLatch){
        super("蒸罐", countDownLatch);
    }

    @Override
    public void check() {
        System.out.println("正在检查[" + this.getStation() + "]...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("检查[" + this.getStation() + "]完毕，可以发车。");
    }

}

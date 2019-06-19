package com.zookeeper.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-09-10 14:58
 */
public abstract class DangerCenter implements Runnable {

    private String station;
    private CountDownLatch countDownLatch;
    private boolean ok = false;

    public DangerCenter(String station, CountDownLatch countDownLatch){
        this.station = station;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            check();
            ok = true;
        } catch(Exception e) {
            e.printStackTrace();
            ok = false;
        } finally {
            if(countDownLatch != null){
                countDownLatch.countDown();
            }
        }
    }

    //蒸罐、石油、轮胎、刹车
    public abstract void check();

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}

package com.zookeeper.CountDownLatch;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-09-10 15:08
 */
public class CountDownLatchMain {

    private static List<DangerCenter> stationList;
    private static CountDownLatch countDownLatch;

    public static void main(String[] args) throws InterruptedException {
        countDownLatch = new CountDownLatch(4);
        stationList = Arrays.asList(
                new LunTaiCheck(countDownLatch),
                new ShaCheCheck(countDownLatch),
                new ShiYouCheck(countDownLatch),
                new ZhengGuanCheck(countDownLatch)
        );

        Executor executor = new ThreadPoolExecutor(stationList.size(), 200,
                0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        for (DangerCenter station : stationList) {
            executor.execute(station);
        }

        countDownLatch.await();

        for (DangerCenter station : stationList) {
            if(!station.isOk()){
                return;
            }
        }
        System.out.println("检查完成，发车。");
    }

}

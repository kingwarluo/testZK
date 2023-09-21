package com.limiter.slidingwindow;

/**
 * 需求：一分钟允许最多100次访问
 * 解决：滑动窗口限流
 * 思路：将1分钟划分为6个格子，每过10s移动一个格子，<important>如果移动后格子不在窗口内，则将窗口平移(超过1分钟后，窗口平移10秒)</important>，
 * 每次请求过后统计所有格子的请求总数，超出100则限流。
 * @author jianhua.luo
 * @date 2021/6/7
 */
public class SlidingWindow {

    private long[][] arr = {{0}, {0}, {0}, {0}, {0}, {0}};

    private final int max = 100;// 窗口请求最大次数

    private long time = System.currentTimeMillis();// 窗口开始时间

    private long moveTime = 10000;// 单位ms，移动间隔时间

    public void trafficMonitoring() {

        long nowTime = System.currentTimeMillis();// 请求进来的时间

        int index = (int) ((nowTime - time) / moveTime + 1);

        if (index > arr.length) {
            // 不在窗口内，将滑动窗口平移
            for (int i = 0; i < index - arr.length; i++) {
                // 将数组平移
                for (int j = 0; j < arr.length - 1; j++) {

                    arr[j][0] = arr[j + 1][0];
                }
                // 将起始时间也向前推进一个窗口
                time += moveTime;
            }
            // 本次插入的窗口为最后一个窗口
            index = arr.length - 1;
            if (limited()) {
                arr[index][0] = 0;
                return;
            }
            arr[index][0] = 1;
        } else {
            if (limited()) {
                return;
            }
            // 加上本次请求数
            long count = arr[index - 1][0];
            // 加上本次请求数
            arr[index - 1][0] = count + 1;
        }
        System.out.println("======================");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[" + i + "]=" + arr[i][0]);
        }
    }

    public boolean limited() {
        // 计算窗口总的请求数是否小于阈值
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i][0];
        }
        if (total >= max) {
            System.out.println("超过最大请求数，拦截请求");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindow sw = new SlidingWindow();
        for (int i = 0; i < 1000; i++) {
            sw.trafficMonitoring();
            Thread.sleep(100);
        }
    }

}

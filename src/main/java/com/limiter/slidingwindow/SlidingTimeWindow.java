package com.limiter.slidingwindow;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * 滑动窗口
 *
 * @author jianhua.luo
 * @date 2021/6/7
 */
@Data
public class SlidingTimeWindow {

    /**
     * 单位时间分割多少块
     */
    private int slot;
    /**
     * 单位时间的次数上限
     */
    private long limit;
    /**
     * 单位时间
     */
    private TimeUnit timeUnit;
    /**
     * 记录窗口滑动到的node
     */
    private Node lastNode;
    /**
     * 单位时间每块时间长度
     */
    private long slotTime;

    SlidingTimeWindow(int slot, long limit, TimeUnit timeUnit) {
        this.slot = slot;
        this.limit = limit;
        this.timeUnit = timeUnit;
        init();
    }

    private void init() {
        Node currentNode = null;
        long current = System.currentTimeMillis();
        for (int i = 0; i < slot; i++) {
            // 上个节点为空，是第一次初始化
            if (lastNode == null) {
                lastNode = new Node(current, 0, i+1);
                currentNode = lastNode;
            } else {
                lastNode.setNext(new Node(current, 0, i+1));
                lastNode = lastNode.getNext();
            }
        }
        // 设置最后一个节点的下一个节点为第一个节点
        lastNode.setNext(currentNode);
        slotTime = timeUnit.toSeconds(100) / slot;
    }

    /**
     * 这个方法检测是否超限，未超限，次数+1
     *
     * @author jianhua.luo
     * @date 2021/6/7
     */
    public synchronized boolean checkAndAdd() {
        reset();
        long sum = getSum();
        System.out.println(sum);
        if (sum >= limit) {
            return false;
        }
        lastNode.addCounter();
        return true;
    }

    /**
     * 滑动窗口
     *
     * @author jianhua.luo
     * @date 2021/6/7
     */
    public void reset() {
        long currentTimeMillis = System.currentTimeMillis();
        long time = lastNode.getTime();
        int count = (int) ((currentTimeMillis - time) / slotTime);
        if (count > slot) {
            count = slot;
        }
        reset(count, currentTimeMillis);
    }

    private void reset(int num, long currentTimeMillis) {
        if (num < 0) {
            return;
        }
        Node currentNode = lastNode;
        for (int i = 0; i < num; i++) {
            currentNode = currentNode.getNext();
        }
        currentNode.setTime(currentTimeMillis);
        currentNode.setCounter(0);
        lastNode = currentNode;
    }

    /**
     * 获取当前窗口的总数
     *
     * @author jianhua.luo
     * @date 2021/6/7
     */
    private long getSum() {
        long sum = 0;
        Node currentNode = lastNode;
        for (int i = 0; i < slot; i++) {
            sum += currentNode.getCounter();
            currentNode = currentNode.getNext();
        }
        return sum;
    }

    /**
     * 窗口细化后的单元格
     *
     * @author jianhua.luo
     * @date 2021/6/7
     */
    @Data
    class Node {

        /**
         * 单元格起始时间
         */
        private long time;
        /**
         * 单元格调用次数
         */
        private long counter;
        /**
         * 下一个单元格
         */
        private Node next;
        /**
         * 唯一标识
         */
        private int id;

        public void addCounter() {
            counter++;
        }

        public Node(long time, long counter, int id) {
            this.time = time;
            this.counter = counter;
            this.id = id;
        }

    }

}

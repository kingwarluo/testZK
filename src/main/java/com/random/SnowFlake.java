package com.random;

/**
 * 雪花算法
 * 使用一个 64 bit 的 long 型的数字作为全局唯一 id：其中 1 个 bit 是不用的，然后用其中的 41 bit 作为毫秒数，用 10 bit 作为工作机器 id，12 bit 作为序列号。
 * 例如：第一个部分，是 1 个 bit：0，这个是无意义的。
 * 第二个部分是 41 个 bit：表示的是时间戳。
 * 第三个部分是 5 个 bit：表示的是机房 id，10001。
 * 第四个部分是 5 个 bit：表示的是机器 id，1 1001。
 * 第五个部分是 12 个 bit：表示的序号，就是某个机房某台机器上这一毫秒内同时生成的 id 的序号，0000 00000000。
 *
 *  <问题>
 *      1、还需做并发控制
 *  </问题>
 * @author jianhua.luo
 * @date 2020/1/17
 */
public class SnowFlake {

    /**
     * 机器id
     */
    private long workerId;
    /**
     * 机房id
     */
    private long dateCenterId;
    /**
     * 一毫秒内生成的多个id的最新序号
     */
    private long sequence;

    public SnowFlake(long workerId, long dataCenterId, long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("data center Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dateCenterId = dataCenterId;
        this.sequence = sequence;
    }

    /**
     * 机器位数
     */
    private long workerIdBits = 5L;
    /**
     * 最大机器id，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 机房中心位数
     */
    private long dataCenterIdBits = 5L;
    /**
     * 最大机房id，就是5 bit最多只能有31个数字，机房id最多只能是32以内
     */
    private long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    /**
     * 序列号位数
     */
    private long sequenceBits = 12L;
    /**
     * 机器左移位数，12
     */
    private long workerIdShift = sequenceBits;
    /**
     * 机房左移位数，12 + 5
     */
    private long dataCenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间戳左移位数，12 + 5 + 5
     */
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    /**
     * 异或操作，就是左移几位，就是2的位数次方 - 1，就是这个数值
     */
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
     */
    private final static long twepoch = 1288834974657L;
    /**
     * 上一次生成序号的时间（ms）
     */
    private long lastTimestamp = -1L;

    /**
     * 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
     * @return
     */
    public synchronized long nextId() {
        // 获取当前时间戳，单位是毫秒
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timestamp) {
            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来
            // 这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;
            //如果超出了，等待下一毫秒
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (dateCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    /**
     * 等待直到下一毫秒
     *
     * @author jianhua.luo
     * @date 2020/2/25
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {

        SnowFlake worker = new SnowFlake(1, 1, 1);
        long startTime = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            System.out.println(worker.nextId());
        }
        System.out.println(String.format("30个号耗时：%sus", System.nanoTime() - startTime));
    }

}

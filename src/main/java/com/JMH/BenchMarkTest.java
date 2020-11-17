package com.JMH;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JMH测试类
 *
 * @author jianhua.luo
 * @date 2020/11/17
 */

//对应 Mode 选项，用来修饰类和方法都可以
//Throughput： 整体吞吐量，比如 QPS，单位时间内的调用量等；
//AverageTime： 平均耗时，指的是每次执行的平均时间。如果这个值很小不好辨认，可以把统计的单位时间调小一点；
//SampleTime： 随机取样，这和我们在第一课时里聊到的 TP 值是一个概念；
//SingleShotTime： 如果你想要测试仅仅一次的性能，比如第一次初始化花了多长时间，就可以使用这个参数，其实和传统的 main 方法没有什么区别；
//All： 所有的指标，都算一遍，你可以设置成这个参数看下效果。
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
// 输出的时间单位
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 预热，预热5次，每次1秒，执行效果：
//    # Warmup Iteration   1: 244874.240 ops/ms
//    # Warmup Iteration   2: 430671.267 ops/ms
//    # Warmup Iteration   3: 565715.861 ops/ms
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
// 真正的迭代，迭代5次，每次1秒，执行效果
//    Iteration   1: 587299.969 ops/ms
//    Iteration   2: 497493.833 ops/ms
//    Iteration   3: 427236.271 ops/ms
//    Iteration   4: 187860.188 ops/ms
//    Iteration   5: 145971.996 ops/ms
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
// fork 的值一般设置成 1，表示只使用一个进程进行测试；如果这个数字大于 1，表示会启用新的进程进行测试；
// 但如果设置成 0，程序依然会运行，不过是这样是在用户的 JVM 进程上运行的，可以看下下面的提示，但不推荐这么做。
//
// 在这里分享一个小技巧。其实 fork 注解有一个参数叫作 jvmArgsAppend，我们可以通过它传递一些 JVM 的参数。
// @Fork(value = 3, jvmArgsAppend = {"-Xmx2048m", "-server", "-XX:+AggressiveOpts"})
@Fork(1)
// fork 是面向进程的，而 Threads 是面向线程的。指定了这个注解以后，将会开启并行测试。如果配置了 Threads.MAX，则使用和处理机器核数相同的线程数。
@Threads(2)
// Scope 有如下三种值：
//Benchmark ：表示变量的作用范围是某个基准测试类。
//Thread ：每个线程一份副本，如果配置了 Threads 注解，则每个 Thread 都拥有一份变量，它们互不影响。
//Group ：联系上面的 @Group 注解，在同一个 Group 里，将会共享同一个变量实例。
@State(Scope.Thread)
public class BenchMarkTest {

    @Benchmark
    public long shift() {
        long t = 455565655225562L;
        long a = 0;
        for (int i = 0; i < 1000; i++) {
            a = t >> 30;
        }
        return a;
    }

    @Benchmark
    public long div() {
        long t = 455565655225562L;
        long a = 0;
        for (int i = 0; i < 1000; i++) {
            a = t / 1024 / 1024 / 1024;
        }
        return a;
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(BenchMarkTest.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();
        new Runner(opts).run();
    }

}

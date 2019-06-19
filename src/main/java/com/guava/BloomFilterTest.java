package com.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.stream.IntStream;

public class BloomFilterTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {

        //假设我们想要创建一个布隆过滤器容忍0.01的误差率，估计大约有500次插入
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 500, 0.01);
        filter.put(1);
        filter.put(2);
        filter.put(3);

        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(100));
    }

    public static void test2() {
        BloomFilter<Integer> filter = BloomFilter.create( Funnels.integerFunnel(),  5, 0.01);

        IntStream.range(0, 100_000).forEach(filter::put);

        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        System.out.println(filter.mightContain(3));
        System.out.println(filter.mightContain(1_000_000));
    }

}

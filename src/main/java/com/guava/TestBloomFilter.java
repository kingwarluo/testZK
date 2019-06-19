package com.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

/**
 * @author KingWarLuo（jianhua.luo）
 * @description: ${description}
 * @date 2018-10-17 15:07
 */
public class TestBloomFilter {

    // 预计元素个数
    private int size = 1024 * 1024;

    private BloomFilter<String> bloom = BloomFilter.create(new Funnel<String>() {
        @Override
        public void funnel(String from, PrimitiveSink into) {
            // 自定义过滤条件 此处不做任何过滤
            into.putString((CharSequence) from, Charset.forName("UTF-8"));
        }
    }, size);

    public void fun() {
        // 往过滤器中添加元素
        bloom.put("布隆过滤器");
        // 查询
        boolean mightContain = bloom.mightContain("布隆过滤器");
        System.out.println(mightContain);
    }

    public static void main(String[] args) {
        TestBloomFilter test = new TestBloomFilter();
        test.fun();
    }

}

package com.random;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;


/**
 * 线性同余算法构造伪随机数
 * 参考文章：https://blog.csdn.net/memray/article/details/8932518
 *
 * @author jianhua.luo
 * @date 2020/1/16
 */
public class RandomNum {

    /**
     * 问题：
     *      1、同一个i值，取出的随机数是同一个：起始值需要设置随机开始，溢出后将i值置0，重新加1
     *      2、如何保证每次取出的随机数不重复：随机数同步起始值，将i值保存在redis，每次取出后加1
     *      3、由于是伪随机数，所以不适用于大型系统
     *      4、需判断负数，和超出最大数后处理
     * @author jianhua.luo
     * @date 2020/1/16
     */
    public static void main(String[] args) {
        Multiset<String> wordMultiSet = HashMultiset.create();
        for (long i = 0; i < 233280 * 3; i++) {
            long j = i % 233280;
            long k = i / 233280;
            long result = (j * 9301 + 49297) % 233280 + k * 233280;
            wordMultiSet.add(String.valueOf(result));
        }
        System.out.println("随机数总数：" + wordMultiSet.size());
        for (String key : wordMultiSet.elementSet()) {
            if(wordMultiSet.count(key) > 1) {
                System.out.println(key + ":" + wordMultiSet.count(key));
            }
        }
    }

}

package com.doublee;

/**
 * @description: double精度问题测试
 *
 * @author jianhua.luo
 * @date 2019/7/4
 */
public class DoubleTest {

    public static void main(String[] args) {
        /**
         * 出现精度问题是由于二进制在小数点转换的时候，出现除或乘不尽的情况，而占用长度固定
         * 例如：将0.6转换成二进制时，整数部分没有歧义，小数部分计算如下
         * 0.6 * 2 = 1.2 ——————- 1
         * 0.2 * 2 = 0.4 ——————- 0
         * 0.4 * 2 = 0.8 ——————- 0
         * 0.8 * 2 = 1.6 ——————- 1
         * 0.6 * 2 = 1.2 ——————- 1
         * 出现 1001 1001 1001......重复，乘不尽的情况，再转换成十进制时，就有精度丢失
         */
        System.out.println(0.05+0.01);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(123.3/100);
        System.out.println(1.01+0.05);
    }

}

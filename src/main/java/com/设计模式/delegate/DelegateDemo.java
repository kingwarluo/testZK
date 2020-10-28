package com.设计模式.delegate;

/**
 * 委派者模式 demo
 * 优点: 对内隐藏实现, 易于扩展; 简化调用;
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class DelegateDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new DelegateTask().doTask();
        }
    }

}

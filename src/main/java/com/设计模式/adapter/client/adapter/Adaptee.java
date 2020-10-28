package com.设计模式.adapter.client.adapter;

/**
 * 被适配的角色
 * 类似原有的service方法
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class Adaptee {

    /**
     * 加入新的方法
     */
    public void greet() {
        System.out.println("greet");
    }

    /**
     * 原类含有的方法
     */
    public void world() {
        System.out.println("world");
    }

}

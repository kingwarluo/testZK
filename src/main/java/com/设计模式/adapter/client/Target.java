package com.设计模式.adapter.client;

/**
 * 提供客户端调用的目标类
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class Target {

    /**
     * 这个方法将来有可能改进
     * 第一版：方法名叫hello
     * 第二版：方法名叫greet
     */
    public void greet() {
        System.out.println("greet");
    }

    public void world() {
        System.out.println("world");
    }

}

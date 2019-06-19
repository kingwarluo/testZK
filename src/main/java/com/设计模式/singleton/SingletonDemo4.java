package com.设计模式.singleton;

/**
 * @author kingwarluo
 * 静态代码块实现单例模式
 * @date 2019/1/12 17:09
 */
public class SingletonDemo4 {

    /**
     * 私有构造方法
     */
    private SingletonDemo4(){}

    private static SingletonDemo4 single;

    /**
     * 静态块
     */
    static {
        single = new SingletonDemo4();
    }

    public static SingletonDemo4 getInstance(){
        return single;
    }

}

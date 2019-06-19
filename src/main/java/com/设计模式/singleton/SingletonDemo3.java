package com.设计模式.singleton;

/**
 * @author kingwarluo
 * 静态内部类实现单例模式
 * @date 2019/1/12 17:09
 */
public class SingletonDemo3 {

    /**
     * 私有构造方法
     */
    private SingletonDemo3(){}

    /**
     * 静态内部类
     */
    private static class InnerObject{
        private static SingletonDemo3 singleton = new SingletonDemo3();
    }

    public static SingletonDemo3 getInstance(){
        return InnerObject.singleton;
    }

}

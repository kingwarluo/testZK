package com.设计模式.singleton;

/**
 * @author kingwarluo
 * 单例模式案例
 * @date 2019/1/12 16:11
 */
public class SingletonDemo2 {

    /**
     * 私有静态属性
     */
    private static SingletonDemo2 singleton = null;

    /**
     * 私有构造方法
     */
    private SingletonDemo2(){}

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonDemo2 getInstance(){
        /**
         * 进行双重检查，保证并发同时，也避免线程等待
         */
        if(singleton == null){
            /**
             * 此为懒汉单例模式，会存在多线程并发问题
             * 所以要对关键代码对类进行加锁，该类所有对象公用一把锁
             */
            synchronized (SingletonDemo2.class){
                /**
                 * 此为可能发生并发代码块
                 */
                if(singleton == null){
                    singleton = new SingletonDemo2();
                }
            }
        }
        return singleton;
    }

}

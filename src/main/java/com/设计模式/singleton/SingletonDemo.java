package com.设计模式.singleton;

/**
 * @author kingwarluo
 * 单例模式案例
 * @date 2019/1/12 16:11
 */
public class SingletonDemo {

    /**
     * 私有静态属性
     */
    private static SingletonDemo singleton = null;

    /**
     * 私有构造方法
     */
    private SingletonDemo(){}

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonDemo getInstance(){
        /**
         * 此为懒汉单例模式，会存在多线程并发问题
         * 所以要对关键代码对类进行加锁，该类所有对象公用一把锁
         * @question 这种方法可以解决并发，但是效率低下。下一个线程想要获取对象，就必须等待上一个线程释放锁。
         * 解决方法见 SingletonDemo2
         */
        synchronized (SingletonDemo.class){
            /**
             * 此为可能发生并发代码块
             */
            if(singleton == null){
                singleton = new SingletonDemo();
            }
        }
        return singleton;
    }

}

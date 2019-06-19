package com.设计模式.singleton;

/**
 * @author kingwarluo
 * 内部枚举类实现单例模式
 * @date 2019/1/12 17:09
 */
public class SingletonDemo8 {

    // 内部枚举类
    private enum EnmuSingleton{
        Singleton;
        private SingletonDemo8 singleton;

        //枚举类的构造方法在类加载是被实例化
        private EnmuSingleton(){
            singleton = new SingletonDemo8();
        }
        public SingletonDemo8 getInstance(){
            return singleton;
        }
    }

    public static SingletonDemo8 getInstance() {
        return EnmuSingleton.Singleton.getInstance();
    }

}

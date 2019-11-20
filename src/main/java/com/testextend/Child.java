package com.testextend;

public class Child extends Parent {

    @Override
    public void doService() {
        System.out.println("子类--服务");
        super.doService();
    }

    @Override
    public void doDispatcher() {
        System.out.println("子类的调度");
    }

}

package com.testextend;

public class Parent {

    public void doService() {
        System.out.println("父类---真正干事的，服务");
        doDispatcher();
    }

    public void doDispatcher() {
        System.out.println("父类的请求调度");
    }

}

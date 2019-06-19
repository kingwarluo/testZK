package com.设计模式.step.validation;

public class CityCheck extends AbstractCheckCell {

    //代码块先于构造函数执行
    {
        System.out.println("代码块");
    }

    public CityCheck() {
        System.out.println("构造函数");
    }

    @Override
    public String checkCell() {
        return null;
    }

}

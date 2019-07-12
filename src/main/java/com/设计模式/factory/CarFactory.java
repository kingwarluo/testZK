package com.设计模式.factory;

/**
 * @description:汽车工厂
 *
 * @author jianhua.luo
 * @date 2019/7/12
 */
public class CarFactory {

    public String getCar(String name) {
        if("Benz".equals(name)) {
            return new Benz().getName();
        } else if("BMW".equals(name)) {
            return new BMW().getName();
        } else {
            return "没有汽车";
        }
    }

}

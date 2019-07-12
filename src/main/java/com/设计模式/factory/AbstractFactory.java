package com.设计模式.factory;

/**
 * @description:抽象工厂类
 *
 * @author jianhua.luo
 * @date 2019/7/12
 */
public abstract class AbstractFactory {

    protected abstract Car getCar();

    /**
     * 这段代码就是动态配置的功能
     * 固定模式的委派
     * @param name
     * @return
     */
    public Car getCar(String name) {
        if("BMW".equals(name)) {
            return new BMWFactory().getCar();
        } else if ("Benz".equals(name)) {
            return new BenzFactory().getCar();
        } else {
            System.out.println("这个产品产不出来");
            return null;
        }
    }

}

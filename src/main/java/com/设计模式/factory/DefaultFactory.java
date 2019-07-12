package com.设计模式.factory;

/**
 * @description:默认工厂
 *
 * @author jianhua.luo
 * @date 2019/7/12
 */
public class DefaultFactory extends AbstractFactory {

    private BenzFactory defaultFactory = new BenzFactory();

    @Override
    protected Car getCar() {
        return defaultFactory.getCar();
    }

}

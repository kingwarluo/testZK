package com.设计模式.factory;

/**
 * @description:奔驰工厂
 *
 * @author jianhua.luo
 * @date 2019/7/12
 */
public class BenzFactory extends AbstractFactory {

    @Override
    public Car getCar() {
        return new Benz();
    }

}

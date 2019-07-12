package com.设计模式.factory;

/**
 * @description:宝马工厂
 *
 * @author jianhua.luo
 * @date 2019/7/12
 */
public class BMWFactory extends AbstractFactory {

    @Override
    public Car getCar() {
        return new BMW();
    }

}

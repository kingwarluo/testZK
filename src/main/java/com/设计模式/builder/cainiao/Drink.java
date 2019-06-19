package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * 饮料
 * @date 2019/1/14 11:05
 */
public abstract class Drink implements Food {

    @Override
    public Packing packing() {
        return new Bottle();
    }

}

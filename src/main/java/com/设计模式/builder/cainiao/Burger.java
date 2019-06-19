package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * 汉堡
 * @date 2019/1/14 11:04
 */
public abstract class Burger implements Food {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

}

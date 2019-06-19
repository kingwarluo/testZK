package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * 肉汉堡
 * @date 2019/1/14 11:07
 */
public class HanBurger extends Burger {
    @Override
    public String name() {
        return "HanBurger";
    }

    @Override
    public int price() {
        return 15;
    }
}

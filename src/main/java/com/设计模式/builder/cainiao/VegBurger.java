package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * 蔬菜汉堡
 * @date 2019/1/14 11:07
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "veg burger";
    }

    @Override
    public int price() {
        return 10;
    }
}

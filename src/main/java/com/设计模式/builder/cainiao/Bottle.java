package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * 瓶装包装
 * @date 2019/1/14 11:03
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "瓶装";
    }
}

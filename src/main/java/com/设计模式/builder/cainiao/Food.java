package com.设计模式.builder.cainiao;

/**
 * @author kingwarluo
 * 食物接口
 * @date 2019/1/14 11:00
 */
public interface Food {

    /**
     * 名称
     * @return
     */
    String name();

    /**
     * 包装
     * @return
     */
    Packing packing();

    /**
     * 价格
     * @return
     */
    int price();

}

package com.设计模式.adapter.client.adapter;

/**
 * 类适配器使用的是继承
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public interface TargetInterface {

    /**
     * 这个方法将来有可能改进
     */
    void hello();

    /**
     * 目标点
     */
    void world();

}

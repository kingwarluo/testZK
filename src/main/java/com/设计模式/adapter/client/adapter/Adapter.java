package com.设计模式.adapter.client.adapter;

/**
 * 类适配器角色
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class Adapter extends Adaptee implements TargetInterface {

    @Override
    public void hello() {
        super.greet();
    }
}

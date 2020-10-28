package com.设计模式.adapter.client.adapter;

/**
 * 对象适配器是使用委派
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class Adapter2 implements TargetInterface {

    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void hello() {
        adaptee.greet();
    }

    @Override
    public void world() {
        adaptee.world();
    }
}

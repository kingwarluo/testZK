package com.spring;

/**
 * @author kingwarluo
 * 代理实现类
 * @date 2019/1/23 17:38
 */
public class DemoImpl implements IDemo {
    @Override
    public void getName() {
        System.out.println("proxy name");
    }

    @Override
    public void getValue() {
        System.out.println("proxy value");
    }
}

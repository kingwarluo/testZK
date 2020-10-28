package com.设计模式.adapter.client;

import com.设计模式.adapter.client.adapter.Adaptee;
import com.设计模式.adapter.client.adapter.Adapter;
import com.设计模式.adapter.client.adapter.Adapter2;
import com.设计模式.adapter.client.adapter.TargetInterface;

/**
 * 适配器模式 demo
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class ClientDemo {

    /**
     * 让我们看看当API改变时，如何保护Client应用程序不受影响。
     * Target：
     * 第一版：方法名叫hello
     * 第二版：方法名叫greet，API找不到hello()肯定报错，针对API“升级”的解决办法就是创建一个适配器(Adapter)。
     *
     * @param args
     */
    public static void main(String[] args) {
//        // 第一版
//        Target target = new Target();
//        target.hello();
//        target.world();

//        // 第二版  类适配器，使用的是继承
//        TargetInterface target = new Adapter();
//        target.hello();
//        target.world();

        // 第三版  对象适配器，使用的是委派delegate
        Adaptee adaptee = new Adaptee();
        Adapter2 target = new Adapter2(adaptee);
        target.hello();
        target.world();
    }

}

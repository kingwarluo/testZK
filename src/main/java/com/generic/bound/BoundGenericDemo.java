package com.generic.bound;

import com.generic.bound.base.Apple;
import com.generic.bound.base.Food;
import com.generic.bound.base.Fruit;
import com.generic.bound.base.Plate;

/**
 * 泛型上下边界 demo
 * 前提：//    public class Food {}
 *     //
 *     //    public class Fruit extends Food {}
 *     //    public class Meat extends Food {}
 *     //
 *     //    public class Apple extends Fruit {}
 *     //    public class Banana extends Fruit {}
 *     //    public class Beef extends Meat {}
 *     //    public class Pork extends Meat {}
 *
 *     //    public class Plate<T> {
 *     //        private T item;
 *     //        public Plate(T fruit) {item = fruit;}
 *     //        public void set(T fruit) {item = fruit;}
 *     //        public T get() {return item;}
 *     //    }
 *
 * @author jianhua.luo
 * @date 2020/10/30
 */
public class BoundGenericDemo {
    // Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
    // 上边界：p表示能用苹果盘给水果盘赋值
    //
    // Plate<? super Fruit> p = new Plate<Food>(new Food());
    // 下边界：p表示一个能放水果及是水果基类的盘子

    public static void main(String[] args) {
        sideEffects();
    }

    /**
     * 副作用
     */
    public static void sideEffects() {
        // 1、上界<? extends T>不能往里存，只能往外取
        // （1）<? extends T>会使往盘子里放东西方法set()失效，但取东西get()方法还有效
        // （2）取出来的东西只能存放在Fruit或他的基类里，向上造型。
        Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
//        // 不能放入任何元素
//        p.set(new Fruit()); // error
//        p.set(new Apple()); // error
//
//        // 取出来的东西只能放在Fruit或它的基类里
//        Fruit f = p.get();
//        Object o = p.get();
//        Apple a = p.get();
        // 编译器在看到？指Fruit的子类，不能认知到是Fruit？Apple?RedApple?GreenApple?，是用一个占位符capture#1表示，无论想往里插Apple或者其他都不能和capture#1匹配
        // 所以通配符<?>和类型参数<T>的区别在于对编译器来说所有的T都代表同一种类型
        // 比如：public <T> List<T> fill(T... t); 三个T都代表同一种类型，要么String，要么Integer
        // 但通配符就没有这种限制，Plate单纯的就表示盘子，里面放了一个东西，是什么我不知道。

        // 2、下界<? super T>不影响往里存，但往外取只能放在Object对象里
        // （1）使用下界<? super Fruit>会使从旁自立取东西的get()方法部分失效，只能存放到Object对象里
        // （2）set()方法正常
        Plate<? super Fruit> pp = new Plate<Food>(new Food());
//        // set()方法正常
//        pp.set(new Apple());
//        pp.set(new Fruit());
//
//        // 读取出来的东西只能存放在Object类里
//        Apple a = pp.get(); // error
//        Fruit f = pp.get(); // error
//        Object o = pp.get();
        // 因为下界规定了元素的最小粒度下线，实际上是放松了容器元素的类型控制，既然元素是Fruit的基类，那往里存粒度比Fruit小的都可以
        // 但往外读取元素就费劲了，只有所有类的基类Object对象才能装下，但这样的话，元素的类型信息就全部丢失。

        // PECS原则（Producer Extends Consumer Super）
        // Producer Extends生产者使用Extends来确定上界，往里面翻东西来生产
        // Consumer Super消费者使用Super来确定下界，往外取东西来消费
        // 1、频繁往外读取内容的，适合用上界Extends，即Extends可用于的返回类型限定，不能用于参数类型限定
        // 2、经常往里插入的，适合用下界Super，super可用于参数类型限定，不能用于返回类型限定
        // 3、带有super超类型限定的通配符可以向泛型对象用写入，带有extends子类型限定的通配符可以向泛型对象读取
    }

}

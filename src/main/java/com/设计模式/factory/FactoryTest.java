package com.设计模式.factory;

/**
 * @description:工厂测试类
 *
 * @author jianhua.luo
 * @date 2019/7/12
 */
public class FactoryTest {

    public static void main(String[] args) {
        /**
         * 1、第一种，用户只要产品而不在乎产品如何生产，看起来很完美的样子，但大家想想，世界上存在什么都生产的工厂吗？
         * 显然是不存在的，每个汽车品牌都有自己的生产工厂，都有自己的生产技术，映射到spring框架中，我们有很多种的bean需要生产
         * 如果只依靠一个简单工厂来实现，那么我们得在工厂类中嵌套多少个if...else...if啊？
         * 而我们在代码中生产一辆汽车只是new一下就出来了，但在实际操作中不知道需要进行多少操作，加载、注册等操作都将体现在工厂类中
         * 那么这个类就会变得紊乱，管理起来很不方便，所以说每个品牌应该有自己的生产类
         */
//        CarFactory factory = new CarFactory();
//        System.out.println(factory.getCar("Benz"));

        /**
         * 2、根据上述代码看出，不同品牌汽车是由不同工厂生产的，貌似又是很完美的。
         * 但大家看一下测试类，当一个人想去买一辆宝马汽车的时候，那么他要去找宝马工厂给他生产一辆
         * 过几天又想要买一辆奔驰汽车，又得跑到奔驰工厂请人生产，这无疑增加了用户的操作复杂性。
         * 所以有没有哟中方便的用户操作方法呢。
         */
//        Factory factory = new BenzFactory();
//        System.out.println(factory.getCar().getName());
//        Factory factory2 = new BMWFactory();
//        System.out.println(factory2.getCar().getName());

        DefaultFactory defaultFactory = new DefaultFactory();
        System.out.println(defaultFactory.getCar("BMW").getName());
    }

}

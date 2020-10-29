package com.设计模式.decorator;

/**
 * 装饰者模式 demo
 *
 * 优点：
 *  1、装饰者模式和继承都是要扩展对象的功能，但是装饰者可以比继承提供更多的灵活性
 *  2、通过使用不同具体装饰者类及他们不同的组合顺序，可以得到不同装饰者后具有不同行为或者状态的对象。比如
 *  3、符合开闭原则-->软件中的对象，对于扩展是开放的，对于修改是封闭的。
 * 缺点：
 *  1、增加了抽象装饰者类和具体装饰者类，一定程度增加了系统的复杂度，加大了系统的学习和理解成本
 *  2、灵活性也意味着更容易出错，对于多次被多次修饰的对象，调试时寻找错误可能要寻找多个地方
 *
 * 举例：
 *  1、
 * @author kingwarluo
 * @date 2019/1/12 17:53
 */
public class DecoratorDemo {

    public static void main(String[] args) {

        // 透明的装饰者模式：要求客户端完全针对抽象编程（依赖倒置原则）。
        //               装饰者模式的透明性要求客户端不应该声明具体构件类型和具体装饰者类型，而应该全部声明为抽象构件类型，当然调用的也是抽象构件类声明的接口方法。
        // 给圆形加上红色和绿色特性，并画出
        StringBuilder sb = new StringBuilder();
        Shape circle = new CircleShape();
        circle = new RedShapeDecorator(circle);
        circle = new GreenShapeDecorator(circle);
        circle.draw(sb);
        System.out.println(sb.toString());

        // 半透明装饰者模式：允许用户在客户端声明具体装饰者类型的对象，允许在具体装饰者中新增方法且客户端可以调用这些新增的方法。
        // 给长方形加上红色特性，不画出
        StringBuilder sb1 = new StringBuilder();
        Shape rectangle = new RectangleShape();
        RedShapeDecorator redRectangle = new RedShapeDecorator(rectangle);
        redRectangle.addRed(sb1);
        System.out.println(sb1.toString());
    }

}

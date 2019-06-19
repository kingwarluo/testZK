package com.设计模式.decorator;

/**
 * @author kingwarluo
 * @{description}
 * @date 2019/1/12 17:53
 */
public class DecoratorDemo {

    public static void main(String[] args) {
        CircleShape circle = new CircleShape();

        Shape redShape = new RedDecoratorShape(new CircleShape());
        Shape redRectangle = new RedDecoratorShape(new RectangleShape());
        circle.draw();

        redShape.draw();
        redRectangle.draw();

    }

}

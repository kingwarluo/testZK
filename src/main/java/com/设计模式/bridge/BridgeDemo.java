package com.设计模式.bridge;

/**
 * 桥接模式
 * 下例：可以使用相同的抽象类方法但是不同的桥接实现类，来画出不同颜色的圆。
 * @author kingwarluo
 * @date 2022/2/11 13:58
 */
public class BridgeDemo {

    public static void main(String[] args) {
        Shape redCircle = new Circle(1, 1, 1, new RedCircle());
        Shape greenCircle = new Circle(1, 1, 1, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }

}

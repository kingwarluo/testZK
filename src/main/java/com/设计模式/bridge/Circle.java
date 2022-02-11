package com.设计模式.bridge;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/2/11 14:00
 */
public class Circle extends Shape {

    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawApi drawApi) {
        super(drawApi);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        this.drawApi.drawCircle(radius, x, y);
    }
}

package com.设计模式.decorator;

/**
 * @author kingwarluo
 * @{description}
 * @date 2019/1/12 17:51
 */
public abstract class DecoratorShape implements Shape {

    private Shape shape = null;

    public DecoratorShape(Shape shape){
        this.shape = shape;
    }

    public void draw() {
        shape.draw();
    }
}

package com.设计模式.decorator;

/**
 * @author kingwarluo
 * @{description}
 * @date 2019/1/12 17:47
 */
public class RedDecoratorShape extends DecoratorShape {

    public RedDecoratorShape(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        super.draw();
        setRedShap();
    }

    protected void setRedShap(){
        System.out.println("red");
    }
}

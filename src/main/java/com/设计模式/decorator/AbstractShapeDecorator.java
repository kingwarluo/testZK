package com.设计模式.decorator;

/**
 * 抽象装饰者
 *
 * @author kingwarluo
 * @date 2019/1/12 17:51
 */
public abstract class AbstractShapeDecorator implements Shape {

    private Shape shape;

    public AbstractShapeDecorator(Shape shape){
        this.shape = shape;
    }

    public void draw(StringBuilder sb) {
        shape.draw(sb);
    }
}

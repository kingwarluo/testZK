package com.设计模式.decorator;

/**
 * 具体装饰者
 *
 * @author kingwarluo
 * @date 2019/1/12 17:47
 */
public class RedShapeDecorator extends AbstractShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(StringBuilder sb) {
        super.draw(sb);
        addRed(sb);
    }

    protected void addRed(StringBuilder sb){
        sb.append("red").append("_");
    }
}

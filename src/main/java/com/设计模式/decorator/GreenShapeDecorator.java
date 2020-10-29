package com.设计模式.decorator;

/**
 * 具体装饰者
 *
 * @author kingwarluo
 * @date 2019/1/12 17:47
 */
public class GreenShapeDecorator extends AbstractShapeDecorator {

    public GreenShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(StringBuilder sb) {
        super.draw(sb);
        addGreen(sb);
    }

    protected void addGreen(StringBuilder sb){
        sb.append("green").append("_");
    }
}

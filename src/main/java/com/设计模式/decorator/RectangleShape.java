package com.设计模式.decorator;

/**
 * 具体构件
 *
 * @author kingwarluo
 * @date 2019/1/12 17:47
 */
public class RectangleShape implements Shape {
    @Override
    public void draw(StringBuilder sb) {
        sb.append("retangle").append("_");
    }
}

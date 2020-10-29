package com.设计模式.decorator;

/**
 * 具体构件
 *
 * @author kingwarluo
 * @date 2019/1/12 17:46
 */
public class CircleShape implements Shape {
    @Override
    public void draw(StringBuilder sb) {
        sb.append("circle").append("_");
    }
}

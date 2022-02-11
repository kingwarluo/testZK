package com.设计模式.bridge;

/**
 * 抽象类
 * @author kingwarluo
 * @date 2022/2/11 14:00
 */
public abstract class Shape {

    protected DrawApi drawApi;

    public Shape(DrawApi drawApi) {
        this.drawApi = drawApi;
    }

    public abstract void draw();

}

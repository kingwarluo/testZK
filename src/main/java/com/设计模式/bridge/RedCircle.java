package com.设计模式.bridge;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/2/11 13:59
 */
public class RedCircle implements DrawApi {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("red circle");
    }
}

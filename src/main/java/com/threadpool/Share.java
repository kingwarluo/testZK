package com.threadpool;

/**
 * @description:描述
 *
 * @author jianhua.luo
 * @date 2019/6/19
 */
public class Share {

    private int a = 0;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void addA(){
        for (int i = 0; i < 100; i++) {
            a += 1;
        }
    }
}


package com.proxy;

/**
 * @author kingwarluo
 * 书本类
 * @date 2019/1/31 17:18
 */
public class Book {

    private String a = "1";

    public void addA(){
        this.a = this.a +"2";
    }
    public String getA(){
        return this.a;
    }

}

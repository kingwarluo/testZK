package com.genericity;

/**
 * @author kingwarluo
 * @{description}
 * @date 2019/1/18 11:36
 */
public class Demo<T extends Animal> {

    private T ob;
    public Demo(T ob){
        this.ob = ob;
    }

    public void print(){
        ob.print();
    }

}

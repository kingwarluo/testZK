package com.genericity;

/**
 * @author kingwarluo
 * 泛型测试类
 * @date 2019/1/18 11:20
 */
public class Genericity {

    public static void take(Demo<?> a){
        a.print();
    }

    public static void main(String[] args) {
        Demo<Dog> dog = new Demo<>(new Dog());
        take(dog);

        Demo<Cat> cat = new Demo<>(new Cat());
        take(cat);
    }

}

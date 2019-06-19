package com.instof;

/**
 * @author kingwarluo
 * @{description}
 * @date 2019/1/21 16:55
 */
public class InstanceOf {

    public static void main(String[] args) {
        PetDog petDog = new PetDog();
        System.out.println(petDog instanceof Animal);
        System.out.println(petDog instanceof Dog);
        System.out.println(petDog instanceof Color);
        petDog.name();
    }

    interface Animal {
        void name();
    }

    interface Color {
        void color();
    }

    static class Dog implements Animal,Color {

        @Override
        public void name() {
            System.out.println("dog");
        }

        @Override
        public void color() {
            System.out.println("yellow");
        }
    }

    static class PetDog extends Dog implements Animal{

        @Override
        public void name() {
            System.out.println("pet dog");
        }
    }

}

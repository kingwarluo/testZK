package com.trycatch;

public class Try {

    public static void main(String[] args) {
        int a = 1;
        if(a > 0){
            throw new RuntimeException("aaaaa");
        }
        System.out.println("bbbb");
    }

}

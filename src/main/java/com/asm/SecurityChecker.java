package com.asm;

public class SecurityChecker {

    private static int a = 1;

    public static void checkSecurity() { 
        System.out.println("SecurityChecker.checkSecurity ..."); 
        //TODO real security check
    }

    public int getA(){
        return a;
    }

}
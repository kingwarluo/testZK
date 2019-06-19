package com.guava;

import java.util.List;
import java.util.ArrayList;

/**
 * @author kingwarluo
 * 测试toArray
 * @date 2019/1/28 9:42
 */
public class TestToArray {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        String[] a = new String[0];
        String[] s = list.toArray(a);
        System.out.println("s:" + s);
        System.out.println("a:" + a.length);
    }

}

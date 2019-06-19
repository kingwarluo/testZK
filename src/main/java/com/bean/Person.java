package com.bean;

import java.util.*;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-24 11:32
 */
public class Person {

    public static void main(String[] args) {
        Comparator<Integer> c3 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        Comparator<Integer> c1 = (x, y) -> {
            System.out.print(Integer.compare(x, y)+"函数式接口");
            return Integer.compare(x, y);
        };
        c1.compare(1, 2);

        Runnable r2 = () -> System.out.println("hello lambda");
        r2.run();

        List<Integer> ints = Arrays.asList(5,6,7,8,9);

        String a = "Aa";
        String b = "BB";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        Map<String, String> map = new HashMap<String, String>();
        map.put("Aa", "1");
        map.put("BB", "2");
        Iterator<String> i = map.keySet().iterator();
        while (i.hasNext()) {
            String entry = i.next();
            System.out.println("entry:"+entry);
        }
        System.out.println(map.size());
    }

}

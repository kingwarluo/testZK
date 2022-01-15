package com.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

/**
 * reduce方法操作
 * @author kingwarluo
 * @date 2022/1/15 9:49
 */
public class Reduce {

    static List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 18));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 2371, 55));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 3322, 25));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 35020, 15));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 2272, 25));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 2057, 87));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 3120, 99));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 345, 25));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 3375, 14));
            add(new Person("Addison", "Pam", "Java programmer", "female", 3426, 20));
        }
    };

    public static void main(String[] args) {
        System.out.println(sum());
    }

    public static Integer sum() {
//        U result = identity;
//        for (T element : this stream)
//            result = accumulator.apply(result, element)
//        return result;
//        combiner是为了解决多线程冲突问题
//        combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
        return javaProgrammers.stream().reduce(0, (sum, p) -> sum = sum + p.getSalary(), Integer::sum);
    }

    @Data
    @AllArgsConstructor
    static class Person {
        private String name;
        private String nickName;
        private String job;
        private String sex;
        private Integer salary;
        private Integer age;
    }

}

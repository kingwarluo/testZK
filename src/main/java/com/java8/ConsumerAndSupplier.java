package com.java8;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author KingWarLuo（jianhua.luo）
 * @description: ${description}
 * @date 2018-10-27 14:55
 */
public class ConsumerAndSupplier {

    public static void main(String[] args) {
        new ConsumerAndSupplier().consumer();
        new ConsumerAndSupplier().supplier();
    }

    public void consumer(){
        Consumer<People> consumer = people -> {
            people.come(new Person("張三", 55));
            people.come(new Person("李四", 50));
        };
        consumer.accept(this::print);
    }

    public void print(Person person){
        System.out.println(person.toString());
    }

    public void supplier(){
        Supplier<Person> supplier = () -> {
            Person person = new Person();
            person.setName("张三");
            person.setAge(32);
            return person;
        };
    }

}

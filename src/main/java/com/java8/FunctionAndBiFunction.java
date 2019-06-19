package com.java8;

import com.logger.itf.BiFunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author KingWarLuo（jianhua.luo）
 * @description: ${description}
 * @date 2018-10-27 15:46
 */
public class FunctionAndBiFunction {

    public static void main(String[] args) {
        // 方法引用-对象::实例方法
        Consumer<String> consumer = System.out::println;
        consumer.accept("11111");

        // 方法引用-类名::静态方法名
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> Integer.compare(x, y);
        BiFunction<Integer, Integer, Integer> biFunction1 = Integer::compare;
        Integer result = biFunction1.apply(100, 200);
        System.out.println(result);

        // 方法引用-类名::实例方法名
        BiFunction<String, String, Boolean> biFunction2 = (x, y) -> x.equals(y);
        BiFunction<String, String, Boolean> biFunction3 = String::equals;
        boolean isEqual = biFunction3.apply("hello", "world");
        System.out.println(isEqual);


        // 构造方法引用  类名::new
        Supplier<Person> supplier = () -> new Person();
        supplier.get();
        Supplier<Person> supplier1 = Person::new;
        supplier1.get();

        // 构造方法引用 类名::new （带一个参数）
        Function<String, Person> function = (x) -> new Person(x);
        Function<String, Person> function1 = Person::new;
        Person person = function1.apply("kingwarluo");
        System.out.println(person);
    }

}

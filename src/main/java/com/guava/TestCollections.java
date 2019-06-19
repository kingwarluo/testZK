package com.guava;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Predicates.and;

/**
 * @author KingWarLuo（jianhua.luo）
 * @description: ${description}
 * @date 2018-10-17 11:37
 */
public class TestCollections {

    public static void main(String[] args) {

        ImmutableList<Person> array = ImmutableList.of(
            new Person(1, "a"), new Person(2, "46546"),
            new Person(3, "b"), new Person(4, "465461"),
            new Person(5, "c"), new Person(6, "465462")
        );
        List<Person> persons = Lists.newArrayList(
            new Person(1, "a"), new Person(2, "46546"),
            new Person(3, "b"), new Person(4, "465461"),
            new Person(5, "c"), new Person(6, "465462")
        );

        // Guava增强for循环
        String s = Joiner.on(",").skipNulls().join(array);
        // System.out.println(s);

        // 1. 将一个集合元素的某一个或多个字段组成一个新的集合：
        Collection<String> newArr = Collections2.transform(array, new Function<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getName();
            }
        });
        // System.out.println(newArr);

        // 2.筛选出集合中满足某些属性的对象
        Collection<Person> newArr2 = Collections2.filter(array, new Predicate<Person>() {
            @Override
            public boolean apply(Person person) {
                return person.getName() == "46546";
            }
        });
        // System.out.println(newArr2);

        // 3.在集合中找到一个满足条件的对象
        Optional<Person> optional = Iterables.tryFind(array, new Predicate<Person>() {
            @Override
            public boolean apply(Person person) {
                return person.getName() == "46546";
            }
        });
        if(optional.isPresent()) {
            // System.out.println(optional.get());
        }else {
            // System.out.println("not found");
        }

        // 4.Guava也提供了能够让多个条件并列或者同时满足的条件组合方法
        Optional<Person> optional2 = Iterables.tryFind(array, and(new Predicate<Person>() {
            @Override
            public boolean apply(Person person) {
                return person.getId() == 5;
            }
        }, new Predicate<Person>() {
            @Override
            public boolean apply(Person person) {
                return person.getName() == "46546";
            }
        }));

        // 5.guava Collections2中方法：orderPermutations与Permutations
        // orderPermutations先将元素排序，在排列
        // Permutations直接排列
        Collection<List<Person>> collection = Collections2.orderedPermutations(persons);
        // System.out.println(collection);

        Collection<List<Person>> collection2 = Collections2.permutations(persons);
        // System.out.println(collection2);

        // 6.Ordering犀利的比较器
        // 7.Optional优雅的使用null

    }

    // 如何创建不可变的集合：
    public static void howToBuildImmutableList(){
        ImmutableList<Person> personImmutableList =
                ImmutableList.of(new Person(1, "a"), new Person(2, "46546"));

        ImmutableSet<Person> personImmutableSet = ImmutableSet.copyOf(personImmutableList);

        ImmutableMap<String,Person> personImmutableMap = ImmutableMap.<String, Person>builder()
                .put("hell",new Person(1,"46546")).putAll(Maps.newHashMap()) .build();

        ImmutableSet<Integer> numbers = ImmutableSet.of(10, 20, 30, 40, 50);
        // 使用copyOf方法
        ImmutableSet<Integer> another = ImmutableSet.copyOf(numbers);
        // 使用Builder方法
        ImmutableSet<Integer> numbers2 = ImmutableSet.<Integer>builder().addAll(numbers).add(60).add(70).add(80).build();
    }

}

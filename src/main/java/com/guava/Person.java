package com.guava;

/**
 * @author KingWarLuo（jianhua.luo）
 * @description: ${description}
 * @date 2018-10-17 13:43
 */
public class Person implements Comparable<Person> {

    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}

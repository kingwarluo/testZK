package com.clone;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/3/29 14:23
 */
public class Professor implements Cloneable {

    private String name;

    public Professor(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

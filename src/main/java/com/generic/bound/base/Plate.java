package com.generic.bound.base;

public class Plate<T> {
    private T item;
    public Plate(T fruit) {item = fruit;}
    public void set(T fruit) {item = fruit;}
    public T get() {return item;}
}
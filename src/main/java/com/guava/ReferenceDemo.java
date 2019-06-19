package com.guava;

import java.lang.ref.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kingwarluo
 * 强引用、软引用、弱引用、虚引用的区别
 * @date 2019/1/28 10:56
 */
public class ReferenceDemo {

    static class User {

        private String name;

        public User() {}

        public User(String name) {
            this.name=name;
        }

        @Override
        public String toString() {
            return name;
        }

        public void finalize(){
            System.out.println("Finalizing ... " + name);
        }
    }

    private static ReferenceQueue<User> referenceQueue = new ReferenceQueue<User>();
    private static final int size = 10;

    public static void checkQueue(){
       /* Reference<? extends User> reference = null;
        while((reference = referenceQueue.poll())!=null){
            System.out.println("In queue : "+reference.get());
        }*/
        Reference<? extends User> reference = referenceQueue.poll();
        if(reference != null){
            System.out.println("In queue : " + reference.get());
        }
    }

    /**
     * 软引用：内存不足时会被GC回收，主要用来做敏感数据的缓存。
     */
    public static void testSoftReference() {
        Set<SoftReference<User>> softReferenceSet = new HashSet<SoftReference<User>>();
        for (int i = 0; i < size; i++) {
            SoftReference<User> ref = new SoftReference<User>(new User("Soft " + i), referenceQueue);
            System.out.println("Just created: " + ref.get());
            softReferenceSet.add(ref);
        }
        System.gc();
        checkQueue();
    }

    /**
     * 弱引用：被GC扫描到就回收，ThreadLocal就是用弱引用
     */
    public static void testWeaKReference() {
        Set<WeakReference<User>> weakReferenceSet = new HashSet<WeakReference<User>>();
        for (int i = 0; i < size; i++) {
            WeakReference<User> ref = new WeakReference<User>(new User("Weak " + i), referenceQueue);
            System.out.println("Just created: " + ref.get());
            weakReferenceSet.add(ref);
        }
        System.gc();
        checkQueue();
    }

    /**
     * 虚引用：任何时候都可能被GC回收，需配合队列使用，主要用来跟踪对象垃圾回收的活动
     */
    public static void testPhantomReference() {
        Set<PhantomReference<User>> phantomReferenceSet = new HashSet<PhantomReference<User>>();
        for (int i = 0; i < size; i++) {
            PhantomReference<User> ref =
                    new PhantomReference<User>(new User("Phantom " + i), referenceQueue);
            System.out.println("Just created: " + ref.get());
            phantomReferenceSet.add(ref);
        }
        System.gc();
        checkQueue();
    }

    public static void main(String[] args) {
        WeakReference<User> softReference = new WeakReference<User>(new User("kingwarluo"));
        testSoftReference();
        testWeaKReference();
        testPhantomReference();


    }

}

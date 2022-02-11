package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:Object内存判断
 * 谨慎使用，可能导致机器宕机
 * vm options : -Xmx5m  -Xms5m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.bin
 * 设置-Xmx和-Xms相同是为了保证堆内存不自动扩展
 * @author jianhua.luo
 * @date 2019/7/12
 */
public class ObjectAllocate {

    static int i = 0;

    public static void createObject() {
        List<Node> list = new ArrayList<Node>();
        try {
            while(true) {
                list.add(new Node(i));
                i++;
            }
        } catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createObject();
    }

    private static class Node {

        private int i;

        public Node(int i) {
            i = i;
        }

    }
}

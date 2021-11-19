package com.javaagent;

/**
 * 探针技术初识
 * 在VM_OPTIONS 加上 -javaagent:D:\IdeaProjects\testZK\java-agent-probe\target\probe.jar=Hello1
 *
 * @author w.jianhua.luo@abite.com
 * @date 2021/11/19
 */
public class AgentTest {

    public static void main(String[] args) {
        System.out.println("===========AgentTest 执行==========");
        sayHello();
        sayHello2("2222222222");
    }

    public static void sayHello() {
        try {
            Thread.sleep(2000);
            System.out.println("hello world!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sayHello2(String hello) {
        try {
            Thread.sleep(1000);
            System.out.println(hello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

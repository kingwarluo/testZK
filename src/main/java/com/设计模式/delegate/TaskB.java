package com.设计模式.delegate;

/**
 * 具体任务角色
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class TaskB implements Task {

    @Override
    public void doTask() {
        System.out.println("任务B");
    }
}

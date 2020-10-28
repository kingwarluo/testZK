package com.设计模式.delegate;

import java.util.Random;

/**
 * 委派者角色
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class DelegateTask implements Task {

    @Override
    public void doTask() {
      if(new Random().nextBoolean()) {
          new TaskA().doTask();
      }else {
          new TaskB().doTask();
      }
    }

}

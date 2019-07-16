package com.threadpool;

/**
 * @description:sonarLint说synchronized关键字不能锁基础类型
 * （String, int等），这里测试下
 *
 * @author jianhua.luo
 * @date 2019/7/8
 */
public class SyncBasicType {

    public static final String LOCK = "aaa".intern();

    public static void main(String[] args) {

    }

    public void test() {
        synchronized (LOCK) {

        }
    }

}

package com.zookeeper.crud;

import org.apache.zookeeper.AsyncCallback;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-15 17:39
 */
public class CreateCallBack implements AsyncCallback.StringCallback {

    @Override
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("创建了一个节点。" + i + "," + s + "," + o + "," + s1);
    }

}

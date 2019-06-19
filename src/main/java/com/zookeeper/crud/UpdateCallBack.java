package com.zookeeper.crud;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-15 17:39
 */
public class UpdateCallBack implements AsyncCallback.StatCallback {

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        System.out.println("修改了一个节点。" + i + "," + s + "," + o + "," + stat);
    }
}

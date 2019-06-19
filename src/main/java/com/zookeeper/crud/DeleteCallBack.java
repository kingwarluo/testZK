package com.zookeeper.crud;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-15 17:39
 */
public class DeleteCallBack implements AsyncCallback.VoidCallback {

    @Override
    public void processResult(int i, String s, Object o) {
        System.out.println("删除了一个节点。" + i + "," + s + "," + o);
    }
}

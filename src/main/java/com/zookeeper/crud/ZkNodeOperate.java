package com.zookeeper.crud;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-09-10 18:13
 */
public class ZkNodeOperate implements Watcher {


    public static CountDownLatch countDown = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent event) {
        System.out.println("========================="+ event.getType()+"=======================");
        try{
            if(Event.EventType.NodeDataChanged.equals(event.getType())){
                byte[] bytes = new ZkCRUD().getZookeeper().getData("/kingwarluo2", false, null);
                System.out.println("修改后的值：" + new String(bytes));
                countDown.countDown();
            }else if(Event.EventType.NodeCreated.equals(event.getType())){

            }else if(Event.EventType.NodeDeleted.equals(event.getType())){

            }else if(Event.EventType.NodeChildrenChanged.equals(event.getType())){

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(event.getType() + "事件执行完成。");
        }
    }
}

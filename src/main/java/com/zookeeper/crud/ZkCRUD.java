package com.zookeeper.crud;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-15 17:26
 */
public class ZkCRUD implements Watcher {

    private ZooKeeper zookeeper;
    private static Stat stat = new Stat();

    public static String SERVER_IP_PORT = "10.112.11.101:3001";
    public static int timeout = 5000;

    public static CountDownLatch countDown = new CountDownLatch(1);

    public ZkCRUD() {}

    public ZkCRUD(String host){
        try{
            zookeeper =  new ZooKeeper(host, timeout, this);
        } catch(Exception e){
            e.printStackTrace();
            if(zookeeper != null){
                try {
                    zookeeper.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
//            research();
//            create();
//            update();
//            delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //触发zk watcher event type的方法，对节点进行持续监控
    public void keepListener() {
        ZkCRUD zk = new ZkCRUD(SERVER_IP_PORT);
        try {
//            zk.getZookeeper().getData("/kingwarluo4", false, stat);
//            zk.getZookeeper().setData("/kingwarluo4", "aaaa".getBytes(), 2);
            if(zk.getZookeeper().exists("/kingwarluo4", true) == null) {
                zk.getZookeeper().create("/kingwarluo4", "create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void research() throws Exception {
        ZkCRUD zk = new ZkCRUD(SERVER_IP_PORT);
        byte[] bytes = zk.getZookeeper().getData("/kingwarluo", true, stat);
        String result = new String(bytes);
        System.out.println(result);
        countDown.countDown();
    }

    public static void create() throws Exception {
        String ctx = "{create:success}";
        //同步
//        new ZkCRUD(SERVER_IP_PORT).getZookeeper().create("/kingwarluo", "kingwarluo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //异步callback
        new ZkCRUD(SERVER_IP_PORT).getZookeeper().create("/kingwarluo321", "kingwarluo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new CreateCallBack(), ctx);
        Thread.sleep(2000);
    }

    public static void update() throws IOException, KeeperException, InterruptedException {
        String ctx = "{create:success}";
        //同步
        new ZkCRUD(SERVER_IP_PORT).getZookeeper().setData("/kingwarluo2", "luojian2hua".getBytes(), 2);
        //异步callback
//        new ZkCRUD(SERVER_IP_PORT).getZookeeper().setData("/kingwarluo2", "luojianhua".getBytes(), 1, new UpdateCallBack(), ctx);
//        Thread.sleep(2000);
    }

    public static void delete() throws IOException, KeeperException, InterruptedException {
        String ctx = "{create:success}";
        //同步
        new ZkCRUD(SERVER_IP_PORT).getZookeeper().delete("/kingwarluo", 2);
        //异步callback
//        new ZkCRUD(SERVER_IP_PORT).getZookeeper().delete("/kingwarluo2", 1, new DeleteCallBack(), ctx);
//        Thread.sleep(2000);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("========================="+ event.getType()+"=======================");
        try{

            if(Watcher.Event.EventType.NodeDataChanged.equals(event.getType())){
                ZkCRUD zk = new ZkCRUD(SERVER_IP_PORT);
                byte[] bytes = zk.getZookeeper().getData("/kingwarluo2", false, null);
                System.out.println("修改后的值：" + new String(bytes));
                countDown.countDown();
            }else if(Watcher.Event.EventType.NodeCreated.equals(event.getType())){

            }else if(Watcher.Event.EventType.NodeDeleted.equals(event.getType())){

            }else if(Watcher.Event.EventType.NodeChildrenChanged.equals(event.getType())){

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(event.getType() + "事件执行完成。");
        }
    }

    public ZooKeeper getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
    }
}

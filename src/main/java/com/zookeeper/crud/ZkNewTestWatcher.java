package com.zookeeper.crud;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ZkNewTestWatcher implements Watcher {

    /** zookeeper实例变量 */
    private ZooKeeper zk = null;
    /** session失效时间 */
    private int session_timeout = 120*1000; //120秒
    /** zookeeper服务器连接地址 */
    private String connection_add = "localhost:3001";
    /** 信号量，用于阻塞主线程等待客户端链接zookeeper服务成功后通知主线程往下继续执行 */
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    /** 操作的根节点 */
    private String root_node = "/watcher";
    /** 操作的子节点 */
    private String child_node = "/watcher/children";
    /** 操作的多级子节点监听 */
    private String multi_node = "/watcher/children/level2";
    /** 操作的多级子节点监听 */
    private String multi_child_node = "/watcher/children/level2/aaa";
    /** 原子计数器，用来统计process被调用的次数 */
    private AtomicInteger count = new AtomicInteger();

    private CountDownLatch addCountDown = new CountDownLatch(1);

    private String log_main = "【main】:";

    public void connectZookeeper(){
        try {
            close();
            zk = new ZooKeeper(connection_add, session_timeout, this);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close() {
        if(zk != null){
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("【开始处理process方法，event:"+ event + "】");
        delayMillis(1000);
        if(event == null) return;
        String log_process = "Watcher-【  "+count.incrementAndGet()+" 】";
        System.out.println(log_process+"收到Watcher的通知");
        System.out.println(log_process+"连接状态："+event.getState());
        System.out.println(log_process+"事件类型："+event.getType());

        connectZookeeperState(event.getState(), event.getType(), log_process, event.getPath());
    }

    /**
     * 判断客户端连接zookeeper服务的连接状态
     * @param state
     * @param type
     * @param log_process
     * @param path
     */
    private void connectZookeeperState(KeeperState state, EventType type, String log_process, String path) {
        if(KeeperState.SyncConnected == state){
            nodeEventType(type, log_process, path);
        } else if(KeeperState.AuthFailed == state){
            System.out.println(log_process+"权限认证失败");
        } else if(KeeperState.Disconnected == state){
            System.out.println(log_process+"客户端与zookeeper服务器连接失败");
        } else if(KeeperState.Expired == state){
            System.out.println(log_process+"客户端与zookeeper服务端会话失效");
        }
    }

    /**
     * 判断节点的事件类型
     * @param type
     * @param log_process
     * @param path
     */
    private void nodeEventType(EventType type, String log_process, String path) {
        // 没有任何节点，表示创建连接成功(客户端与服务器端创建连接成功后没有任何节点信息)
        if(EventType.None == type){
            System.out.println(log_process+"成功链接zookeeper服务器");
            countDownLatch.countDown(); // 通知阻塞的线程可以继续执行
        } else if (EventType.NodeCreated == type){
            System.out.println(log_process+"zookeeper服务端创建新节点，路径：" + path);
//            delayMillis(2000);
            addCountDown.countDown();
            //zookeeper服务端创建一个新的节点后并对其进行监控,创建完后接着对该节点进行监控,没有此代码将不会在监控该节点
            exists(path, true);
        } else if (EventType.NodeDeleted == type){
            System.out.println(log_process+"zookeeper服务端删除节点，路径：" + path);
        } else if (EventType.NodeDataChanged == type){
            System.out.println(log_process+"节点数据更新，路径：" + path);
            delayMillis(2000);
            // 加载节点数据，才会对节点持续监控
            readNodeData(path, true);
        } else if (EventType.NodeChildrenChanged == type) {
            System.out.println(log_process+"子节点数据，路径：" + path);
        }
    }

    private String readNodeData(String path, boolean needWatch) {
        String data = "";
        try {
            zk.getData(path, needWatch, null);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
        return data;
    }

    /**
     * 判断节点是否存在
     * @param path
     * @param needWatch
     * @return
     */
    public Stat exists(String path, boolean needWatch){
        try {
            return zk.exists(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 清除所有测试节点
     * @param needWatch
     */
    public void deleteAllNode(boolean needWatch) {
        if(exists(child_node, true) != null) {
            deleteNode(child_node);
        }
        if(exists(root_node, true) != null) {
            deleteNode(root_node);
        }
    }

    private void deleteNode(String path) {
        try {
            zk.delete(path, -1);// -1 忽略所有版本号
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个新的节点
     * @param path
     * @param content
     * @param watcher
     * @return
     */
    public boolean createPath(String path, String content, Watcher watcher) {
        Stat stat = null;
        try {
            if(watcher == null){
                stat = exists(path, true);
            }else{
                stat = zk.exists(path, watcher);
            }
            if(stat == null){
                zk.create(path, content.getBytes(),
                        Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);

            }else{
                System.out.println("节点已经存在，无法再创建");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void delayMillis(long mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZkNewTestWatcher watch = new ZkNewTestWatcher();

        watch.connectZookeeper();

        watch.deleteAllNode(true);
//
//        if(watch.createPath(watch.root_node, "watcher", null)){
//            watch.delayMillis(1*1000); // 休息一秒
//        }

        if(watch.createPath(watch.root_node, "测试多级节点", null)) {
//            watch.delayMillis(1*1000); // 休息一秒
            watch.addCountDown.await();
            watch.addCountDown = new CountDownLatch(1);
            if(watch.createPath(watch.child_node, "测试多级子节点", null)){
//                watch.delayMillis(1*1000); // 休息一秒
                watch.addCountDown.await();
            }
        }
        watch.readNodeData(watch.child_node, true);

        watch.delayMillis(10*1000); // 休息一秒
        watch.close();
    }


}

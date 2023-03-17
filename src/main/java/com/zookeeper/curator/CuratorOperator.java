package com.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.file.Path;
import java.util.List;

/**
 * curator连接示例
 * @author kingwarluo
 * @date 2023/3/16 15:02
 */
public class CuratorOperator {

    public CuratorFramework client = null;
    public static final String zkServerPath = "101.43.122.53:2181";

    public static void main(String[] args) throws Exception {
        // 实例化
        CuratorOperator operator = new CuratorOperator();
        boolean started = operator.client.isStarted();
        System.out.println(started);

        // 创建节点
        String nodePath = "/kingwarluo/node1";
//        byte[] data = "luo".getBytes();
//        operator.client.create().creatingParentContainersIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                .forPath(nodePath, data);
//
//        // 更新节点
//        byte[] newData = "king".getBytes();
//        operator.client.setData()
//                .withVersion(2)
//                .forPath(nodePath, newData);
//
//        // 删除节点
//        operator.client.delete()
//                .guaranteed() // 如果删除异常，会一直重试，直到成功
//                .deletingChildrenIfNeeded() // 删除子节点
//                .withVersion(2)
//                .forPath(nodePath);

//        // 查询节点数据
//        Stat stat = new Stat();
//        byte[] data = operator.client.getData().storingStatIn(stat).forPath(nodePath);
//        System.out.println(nodePath + "节点数据为：" + new String(data) + "，版本号：" + stat.getVersion());
//
//        // 查询子节点
//        List<String> childList = operator.client.getChildren().forPath(nodePath);
//        for (String child : childList) {
//            System.out.println(child);
//        }
//
//        // 判断节点是否存在，如果不存在返回空
//        Stat stat1 = operator.client.checkExists().forPath(nodePath);
//        System.out.println(stat1);

//        // watcher监听事件，只会触发一次
//        operator.client.getData().usingWatcher((CuratorWatcher) event -> {
//            System.out.println("路径：" + event.getPath() + "，事件：" + event.getType());
//        }).forPath(nodePath);

//        // watcher 用NodeCache可以监听每一次变化
//        NodeCache nodeCache = new NodeCache(operator.client, nodePath);
//        nodeCache.start(true); // 启动时获取节点数据
//        if(nodeCache.getCurrentData() != null) {
//            System.out.println("节点数据为：" + nodeCache.getCurrentData().getData());
//        } else {
//            System.out.println("节点数据为空");
//        }
//        nodeCache.getListenable().addListener(() -> {
//            String data = new String(nodeCache.getCurrentData().getData());
//            System.out.println("当前节点数据为：" + data);
//        });

        // 为子节点添加watcher
        final PathChildrenCache childrenCache = new PathChildrenCache(operator.client, nodePath, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        List<ChildData> currentData = childrenCache.getCurrentData();
        System.out.println("当前节点的数据");
        for (ChildData child : currentData) {
            System.out.println(child.getPath());
        }
        childrenCache.getListenable().addListener((client, event) -> {
            System.out.println("触发监听：" + event.getType().name());
            if(event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                // 节点初始化
            } else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                // 添加子节点
                System.out.println(event.getData().getPath());
            } else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                // 更新子节点
                System.out.println(event.getData().getPath() + "," + new String(event.getData().getData()));
            } else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                // 删除子节点
                System.out.println(event.getData().getPath());
            }
        });

        Thread.sleep(3000);
        operator.closeZkClient();
        boolean started1 = operator.client.isStarted();
        System.out.println(started1);
    }

    public CuratorOperator() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
//        RetryPolicy retryPolicy = new RetryNTimes(5, 1000);
        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000)
                .namespace("workspace")
                .retryPolicy(retryPolicy)
                .build();
        client.start();
    }

    public void closeZkClient() {
        if(client != null) {
            client.close();
        }
    }

}

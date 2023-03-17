package com.zookeeper.curator.culster;

import jodd.util.StringUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zookeeper.curator.culster.Constant.clusterNode;

/**
 * curator连接示例
 * @author kingwarluo
 * @date 2023/3/16 15:02
 */
public class ServiceA1 {

    public CuratorFramework client = null;
    public static final String zkServerPath = "101.43.122.53:2181";
    private static String service = "ServiceA";
    private static String node = "1";
    private static String serviceName = service + node;
    private static long sleepTime = 3000000;

    public static Map<String, List<String>> nodeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        if(args.length > 0 && StringUtil.isNotEmpty(args[0])) {
            sleepTime = Long.parseLong(args[0]);
        }
        // 实例化
        ServiceA1 operator = new ServiceA1();
        boolean started = operator.client.isStarted();
        System.out.println(started);

        // 为子节点添加watcher
        final PathChildrenCache childrenCache = new PathChildrenCache(operator.client, clusterNode + "/" + service, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener((client, event) -> {
            System.out.println(serviceName + "触发监听：" + event.getType().name());
            if(event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                // 节点初始化
                System.out.println(serviceName + "初始化成功");
                // 添加节点
                operator.client.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(clusterNode + "/" + service + "/" + node, serviceName.getBytes());
            } else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                // 添加子节点
                String path = event.getData().getPath();
                String[] paths = path.split("/");
                String nodeIp = paths[paths.length - 1];
                System.out.println(event.getData().getPath());
                // 添加自己到集群
                addToMap(nodeIp);
                System.out.println(service + "的服务节点为：" + nodeMap);
            } else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                // 更新子节点
                System.out.println(event.getData().getPath() + "," + new String(event.getData().getData()));
            } else if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                // 删除子节点
                System.out.println(event.getData().getPath());
                String path = event.getData().getPath();
                String[] paths = path.split("/");
                String nodeIp = paths[paths.length - 1];
                removeFromMap(nodeIp);
                System.out.println(service + "的服务节点为：" + nodeMap);
            }
        });

        Thread.sleep(sleepTime);

        operator.closeZkClient();
        boolean started1 = operator.client.isStarted();
        System.out.println(started1);
    }

    public static void removeFromMap(String node) {
        // 从集群移除
        List<String> serverList = nodeMap.getOrDefault(serviceName, new ArrayList<>());
        serverList.remove(node);
        nodeMap.put(serviceName, serverList);
    }

    public static void addToMap(String node) {
        // 添加自己到集群
        List<String> serverList = nodeMap.getOrDefault(serviceName, new ArrayList<>());
        serverList.add(node);
        nodeMap.put(serviceName, serverList);
    }

    public ServiceA1() {
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

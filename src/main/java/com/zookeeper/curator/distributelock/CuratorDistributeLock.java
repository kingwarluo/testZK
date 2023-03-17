package com.zookeeper.curator.distributelock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.concurrent.CountDownLatch;

/**
 * Curator做分布式锁
 * @author luojianhua
 * @date 2023/3/17 13:51
 **/
public class CuratorDistributeLock {

    public CuratorFramework client = null;
    public static final String zkServerPath = "101.43.122.53:2181";

    public static void main(String[] args) throws InterruptedException {
        CuratorDistributeLock lock = new CuratorDistributeLock();
        lock.init();

        lock.getLock();
        Thread.sleep(3000);
        lock.releaseLock();
    }

    private CountDownLatch zkCountDownLatch = new CountDownLatch(1);

    private String LOCK_PROJECT = "proj";

    private String bizLock = "bizLock";

    public void init() {
        try {
            if(client.checkExists().forPath("/" + LOCK_PROJECT) == null) {
                client.create().creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath("/" + LOCK_PROJECT);
            }
            // 添加根节点watcher
            addWatcherToLock("/" + LOCK_PROJECT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addWatcherToLock(String path) throws Exception {
        final PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener((client, event) -> {
            System.out.println("子节点事件：" + event.getType());
            if(event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String removePath = event.getData().getPath();
                if(removePath.equals(bizLock)) {
                    zkCountDownLatch.countDown();
                }
            }
        });
    }

    public void getLock() {
        while(true) {
            try {
                client.create().creatingParentContainersIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath("/" + LOCK_PROJECT + "/" + bizLock);
                System.out.println("获取分布式锁成功。。。。。");
                return;
            } catch (Exception e) {
                System.out.println("获取分布式锁失败。。。。。");
                if(zkCountDownLatch.getCount() <= 0) {
                    zkCountDownLatch = new CountDownLatch(1);
                }
                try {
                    zkCountDownLatch.await();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    public boolean releaseLock() {
        try {
            if(client.checkExists().forPath("/" + LOCK_PROJECT + "/" + bizLock) != null) {
                client.delete().forPath("/" + LOCK_PROJECT + "/" + bizLock);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public CuratorDistributeLock() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000)
                .namespace("locks-namespace")
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

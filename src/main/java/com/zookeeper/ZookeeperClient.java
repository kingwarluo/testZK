package com.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-08 11:10
 */
public class ZookeeperClient implements Watcher {

    static Logger log = LoggerFactory.getLogger(ZookeeperClient.class);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException, NoSuchAlgorithmException {
        ZooKeeper zk = new ZooKeeper("localhost:3001", 5000, new ZookeeperClient());
        log.info("连接成功。");
        log.info("当前的状态：{}", zk.getState());
        Thread.sleep(2000);
        log.info("当前的状态：{}", zk.getState());

        zk.create("/kingwarluo2", "kingwarluo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Event.EventType evn = Event.EventType.NodeChildrenChanged;

//        //ACLS
//        List<ACL> acls = new ArrayList<ACL>();
//        Id kingwarluo1 = new Id("digest", AclUtil.getDigestUserPwd("kingwarluo1:123456"));
//        Id kingwarluo2 = new Id("digest", AclUtil.getDigestUserPwd("kingwarluo2:123456"));
//        acls.add(new ACL(ZooDefs.Perms.ALL, kingwarluo1));
//        acls.add(new ACL(ZooDefs.Perms.READ, kingwarluo2));
//        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE, kingwarluo2));
//        //给节点设置权限
//        zk.create("/testauth", "auth".getBytes(), acls, CreateMode.EPHEMERAL);
//        //登陆
//        zk.addAuthInfo("digest", "kingwarluo1:123456".getBytes());
//        //操作节点
//        zk.create();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("接收到事件:{}", watchedEvent);
    }
}

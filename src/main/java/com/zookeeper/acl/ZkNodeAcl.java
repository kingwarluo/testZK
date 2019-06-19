package com.zookeeper.acl;

import com.zookeeper.AclUtil;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-09-15 09:38
 */
public class ZkNodeAcl implements Watcher {

    private ZooKeeper zookeeper = null;
    public static String SERVER_IP_PORT = "10.112.11.101:3001";
    public static int timeout = 5000;
    private static Stat stat = new Stat();

    private ZkNodeAcl() {}

    public ZkNodeAcl(String host) {
        try {
            zookeeper = new ZooKeeper(host, timeout, new ZkNodeAcl());
        } catch (IOException e) {
            if(zookeeper != null) {
                try {
                    zookeeper.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkNodeAcl zk = new ZkNodeAcl(SERVER_IP_PORT);

        List<ACL> acls = new ArrayList<ACL>();
        acls.addAll(buildDigestACL("digest", "cdrwa", "kingwarluo", "kingwarluo"));
        acls.addAll(buildDigestACL("digest", "crwa", "kingwarluo2", "kingwarluo"));

//        zk.createZkNode("/kingwarluo/child", "child".getBytes(), acls);

        zk.getZookeeper().setACL("/kingwarluo/child", acls, 0);
    }

    public static List<ACL> buildDigestACL(String scheme, String auth, String username, String password) {
        List<ACL> acls = new ArrayList<ACL>();
        try {
            Id id = new Id(scheme, AclUtil.getDigestUserPwd(username+":"+password));

            for (int i = 0; i < auth.length(); i++) {
                if(i < auth.length()) {
                    char charAuth = auth.charAt(i);
                    int perm = 0;
                    switch (charAuth) {
                        case 'c' :
                            perm = ZooDefs.Perms.CREATE;
                            break;
                        case 'd' :
                            perm = ZooDefs.Perms.DELETE;
                            break;
                        case 'r' :
                            perm = ZooDefs.Perms.READ;
                            break;
                        case 'w' :
                            perm = ZooDefs.Perms.WRITE;
                            break;
                        case 'a' :
                            perm = ZooDefs.Perms.ADMIN;
                            break;
                        default :
                            break;
                    }
                    if(perm > 0) {
                        acls.add(new ACL(perm, id));
                    }
                }
            }
            return acls;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createZkNode(String path, byte[] bytes, List<ACL> acls) {
        try {
            Stat stat = getZookeeper().exists(path, true);
            if(stat == null) {
                getZookeeper().create(path, bytes, acls, CreateMode.PERSISTENT);
            }else {
                System.out.println("节点已经存在。");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("=================="+ watchedEvent.getType()+"=================");
    }

    public ZooKeeper getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
    }
}

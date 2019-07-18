package com.netease.consistencehash;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @description:模拟一致性hash算法实现
 * 原理：一致性hash算法+虚拟节点+红黑树
 * 一致性hash算法有，CRC32_HASH算法、FNV1_32_HASH算法、KETAMA_HASH算法（MemCache推荐的算法）
 *
 * 假设有N个物理节点，每个节点虚拟100个虚拟节点，散列的分布在hash环的地方，且不重复放置
 * @author jianhua.luo
 * @date 2019/7/18
 */
public class ConsistencyHash {

    /**
     * 虚拟节点数量，100个
     */
    private int virtual_node = 100;

    /**
     * 物理节点列表
     */
    private List<String> nodeList = new ArrayList<String>();

    /**
     * 每个节点对应的虚拟节点
     */
    private Map<String, List<Integer>> toVirtualMap = new HashMap<>();

    /**
     * hash环上的虚拟节点对应的物理节点
     * key表示服务器虚拟节点的hash值，value表示服务器名称
     */
    private SortedMap<Integer, String> roundMap = new TreeMap<>();

    public ConsistencyHash(List<String> nodeList) {
        this.nodeList = nodeList;
    }

    public void addServer() {
        for (String node : nodeList) {
            //用于构造节点名称，重复时，加一继续计算
            int virtual_index = 0;
            //用于计算节点数量，最大为100个
            int virtual_count = 1;
            List<Integer> virtualNodes = new ArrayList<>();
            while(virtual_count <= virtual_node) {
                String nodeName = "node---" + virtual_index;
                int hash = FNV1_32_HASH.getHash(nodeName);
                if(!roundMap.containsKey(hash)) {
                    roundMap.put(hash, node);
                    virtualNodes.add(hash);
                    virtual_count++;
                }
                virtual_index++;
            }
            System.out.println("节点:" + node + "的虚拟节点数量" + virtualNodes.size());
            toVirtualMap.put(node, virtualNodes);
        }
    }

    /**
     * 根据客户端节点，寻找最近的服务节点
     * @param customerHost
     * @return
     */
    public String getServer(String customerHost) {
        //获取节点的hash值
        int hash = FNV1_32_HASH.getHash(customerHost);

        //得到大于该hash值的所有map
        SortedMap<Integer, String> subMap = roundMap.tailMap(hash);

        if(subMap != null && subMap.size() > 0){
            return subMap.get(subMap.firstKey());
        } else {
            return roundMap.get(roundMap.firstKey());
        }
    }

    /**
     * 移除某个节点的服务
     * @param node
     */
    public void removeServer(String node) {
        List<Integer> nodes = toVirtualMap.remove(node);
        for (Integer i : nodes) {
            roundMap.remove(i);
        }
        nodeList.remove(node);
    }

    public static void main(String[] args) throws InterruptedException {
        String hosts = "192.168.0.1:1111,192.168.0.2:1111,192.168.0.3:1111,192.168.0.4:1111,192.168.0.5:1111,192.168.0.6:1111";
        List<String> nodeList = Arrays.asList(hosts.split(","));
        ConsistencyHash consistencyHash = new ConsistencyHash(nodeList);

        consistencyHash.addServer();

        Thread.sleep(1000L);
        String customerHost = "192.168.0.0:1111";
        String node = consistencyHash.getServer(customerHost);
        System.out.println(node);
    }

}

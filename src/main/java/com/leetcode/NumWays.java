package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 传递信息
 *
 * @author jianhua.luo
 * @date 2021/7/7
 */
public class NumWays {

    static List<List<Integer>> list = null;
    static int ways, n, k;

    /**
     *  传递信息
     * @param n         n个玩家
     * @param relation  到达关系
     * @param k         几次到达
     * @param args
     */
    public static void main(String[] args) {
        n = 5;
        int[][] relation = new int[][]{{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        k = 3;
        // 动态规划
        int count = numWays(n, relation, k);
//        // 深度优先 or 广度优先
//        int count = numWays(relation);
        System.out.println(count);
    }

    public static int numWays(int[][] relation) {
        // 先构造{0， [1, 2]}的对应关系
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] relate : relation) {
            int src = relate[0], dst = relate[1];
            list.get(src).add(dst);
        }
//        dfs(0, 0);
        bfs();
        return ways;
    }

    /**
     * 深度优先搜索（没懂）
     *
     *
     * @param index
     * @param steps
     */
    public static void dfs(int index, int steps) {
        if(steps == k) {
            if(index == n - 1) {
                ways++;
            }
            return;
        }
        List<Integer> dstList = list.get(index);
        for (Integer next : dstList) {
            dfs(next, steps + 1);
        }
    }

    /**
     * 广度优先搜索
     */
    public static void bfs() {
        int steps = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        while(!queue.isEmpty() && steps < k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                List<Integer> dstList = list.get(index);
                for (Integer dst : dstList) {
                    queue.offer(dst);
                }
            }
            steps++;
        }

        while(!queue.isEmpty()) {
            int dst = queue.poll();
            if(dst == n - 1) {
                ways++;
            }
        }
    }

    /**
     * 动态规划
     */
    public static int numWays(int n, int[][] relation, int k) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] next = new int[n];
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                next[dst] += dp[src];
            }
            dp = next;
        }
        return dp[n - 1];
    }

}

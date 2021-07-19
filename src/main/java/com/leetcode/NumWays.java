package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LCP 07. 传递信息
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
     * 问题：
     * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     *
     * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
     * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
     * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
     * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     * 例如：
     * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
     * 输出：3
     * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
     *
     *
     * 动态规划方案
     * 三个重要概念：最优子结构(F(10)=F(9)+F(8))、边界（无需继续简化F(1)、F(2)）、状态转移方程(阶段与阶段之间F(n)=F(n-1)+F(n-2))
     * 这里，假设F[i][j]表示第i步时走到j的方法数,最优子结构（F[3][4]=F[2][0-3]）、边界（F[0][0]) = 1）、状态转移方程（F[i][dst] = F[i - 1][src](0 <= i <= k)）
     *
     * 复杂度分析：
     *      时间复杂度：O(k*relation.length)
     *      空间复杂度：O(kn)，因为是（k+1,n）长度，第0步不走
     */
    public static int numWays(int n, int[][] relation, int k) {
        // 由于长度=k，后续有k+1的计算，所以这里指定长度为k+1。
        int[][] f = new int[k+1][n];
        // 边界，题目限制一定是从0开始，定义 第0步时走到0的方法有1种
        f[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] r : relation) {
                int src = r[0], dst = r[1];
                f[i+1][dst] += f[i][src];
            }
        }
        // 由于数组定义[k+1][n]的长度，f[k][n-1]=最后一个数字
        return f[k][n-1];
    }

    /**
     * 优化动态规划（dp）空间复杂度，简化为1维数组
     *
     * 复杂度分析：
     *      时间复杂度：O(k*relation.length)
     *      空间复杂度：O(n)，因为是（n）长度，第0步不走
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public static int numWaysSpace(int n, int[][] relation, int k) {
        int[] f = new int[n];
        // 边界，题目限制一定是从0开始，定义 走到0的方法有1种
        f[0] = 1;
        for (int i = 0; i < k; i++) {
            // 第i步的走法
            int[] next = new int[n];
            for (int[] r : relation) {
                int src = r[0], dst = r[1];
                next[dst] += f[src];
            }
            f = next;
        }
        return f[n-1];
    }

}

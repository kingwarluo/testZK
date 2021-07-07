package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 大餐计数
 *
 * @author jianhua.luo
 * @date 2021/7/7
 */
public class DinnerCount {

    public static void main(String[] args) {
        int[] delicious = new int[]{64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64};
        int count = solution(delicious);
        System.out.println(count);
    }

    //上述朴素解法存在同一个元素被重复计算的情况，因此可以使用哈希表减少重复计算，降低时间复杂度。
    // 具体做法是，使用哈希表存储数组中的每个元素的出现次数，遍历到数组 deliciousness 中的某个元素时，在哈希表中寻找与当前元素的和等于 22 的幂的元素个数，然后用当前元素更新哈希表。
    // 由于遍历数组时，哈希表中已有的元素的下标一定小于当前元素的下标，因此任意一对元素之和等于 22 的幂的元素都不会被重复计算。
    public static int solution (int[] deliciousness) {
        final int MOD = 100000007;
        // 1、先找出最大数
        int maxNum = 0;
        for (int i : deliciousness) {
            maxNum = Math.max(i, maxNum);
        }
        // 2、计算最大和
        int maxSum = maxNum * 2;
        // 3、遍历deliciousness
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int delicious : deliciousness) {
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - delicious, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(delicious, map.getOrDefault(delicious, 0) + 1);
        }
        return pairs;
    }

}

package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 和相同的二元子数组
 *
 * @author jianhua.luo
 * @date 2021/7/7
 */
public class NumSubarraysWithSum {

    public static void main(String[] args) {
        int[] nums = new int[]{1,0,1,0,1};
        int goal = 2;
        int count = numSubarraysWithSum(nums, goal);
        System.out.println(count);
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return method2(nums, goal);
    }

    /**
     * 执行用时： 2313 ms, 在所有 Java 提交中击败了 5.10% 的用户
     * 内存消耗： 41.5 MB, 在所有 Java 提交中击败了 80.27% 的用户
     * 执行耗时过长
     * @param nums
     * @param goal
     * @return
     */
    public static int method1(int[] nums, int goal){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0, j = i;
            while(sum <= goal && j <= nums.length - 1) {
                sum += nums[j++];
                if(sum == goal) {
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀和 + 哈希表
     * @param nums
     * @param goal
     * @return
     */
    public static int method2(int[] nums, int goal){
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }

}

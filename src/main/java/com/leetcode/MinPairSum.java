package com.leetcode;

import java.util.Arrays;

/**
 * 1877. 数组中最大数对和的最小值
 *
 * @author jianhua.luo
 * @date 2021/7/20
 */
public class MinPairSum {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,4,4,5,5,5,5};
        int max = minPairSum(nums);
        System.out.println(max);
    }

    public static int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int mid = n >> 1;

        int[] sum = new int[mid];
        int max = 0;
        for (int i = 0; i < mid; i++) {
            sum[i] = nums[i] + nums[n - 1 - i];
            max = Math.max(sum[i], max);
        }
        return max;
    }

}

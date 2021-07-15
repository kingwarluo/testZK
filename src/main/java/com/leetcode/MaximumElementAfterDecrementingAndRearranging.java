package com.leetcode;

import java.util.Arrays;

/**
 * 1846. 减小和重新排列数组后的最大元素
 *
 * @author jianhua.luo
 * @date 2021/7/15
 */
public class MaximumElementAfterDecrementingAndRearranging {

    public static void main(String[] args) {
        int[] arr = new int[]{100,1,1000};
        int max = maximumElementAfterDecrementingAndRearranging(arr);
        System.out.println(max);
    }

    /**
     * 排序+贪心
     * @param arr
     * @return
     */
    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);

        arr[0] = 1;
        int same = 0;
        for (int i = 1; i < n; i++) {
            if(arr[i] > n) {
                break;
            }
            if(arr[i] - arr[i - 1] == 0) {
                same++;
            } else {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return n - same;
    }

}

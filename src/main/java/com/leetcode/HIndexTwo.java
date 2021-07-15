package com.leetcode;

/**
 * 275. H 指数 II
 *
 * @author jianhua.luo
 * @date 2021/7/12
 */
public class HIndexTwo {

    public static void main(String[] args) {
        int[] citations = new int[]{1,2};
        int high = hIndex(citations);
        System.out.println(high);
    }

    public static int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }

}

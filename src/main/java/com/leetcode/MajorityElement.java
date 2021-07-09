package com.leetcode;

/**
 * 主要元素  17.10. 主要元素
 *
 * @author jianhua.luo
 * @date 2021/7/9
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int major = majorityElement(nums);
        System.out.println(major);
    }

    /**
     * Boyer-Moore 投票算法
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int candicate = 0;
        int count = 0;
        for (int num : nums) {
            if(count == 0) {
                candicate = num;
            }
            if(num == candicate) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : nums) {
            if(num == candicate) {
                count++;
            }
        }
        return count * 2 > nums.length ? count : -1;
    }
}

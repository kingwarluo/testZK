package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 *
 * @author jianhua.luo
 * @date 2021/7/9
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,0};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result);
    }

    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    //
    //注意：答案中不可以包含重复的三元组。

    /**
     * 排序 + 双指针
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);  //对数组排序
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 如果num>0，结束
            if(num > 0) {
                break;
            }
            // 如果和前一个数相等，跳过
            if(i > 0 && num == nums[i - 1]) {
                continue;
            }
            twoSum(result, num, i, nums);
        }
        return result;
    }

    public static void twoSum(List<List<Integer>> result, int num, int i, int[] nums) {
        int start = i + 1;
        int end = nums.length - 1;
        int goal = -num;

        while(start < end) {
            int twoSum = nums[start] + nums[end];
            if(twoSum == goal) {
                List<Integer> target = new ArrayList<>();
                target.add(num);
                target.add(nums[start]);
                target.add(nums[end]);
                result.add(target);
                while(start < end && nums[start] == nums[start + 1]) {
                    start += 1;
                }
                while(start < end && nums[end] == nums[end - 1]) {
                    end -= 1;
                }
                start++;
                end--;
            } else if(twoSum > goal) {
                end--;
            } else if(twoSum < goal) {
                start++;
            }
        }
    }

}

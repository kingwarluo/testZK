package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1838. 最高频元素的频数
 * 
 * @author jianhua.luo
 * @date 2021/7/19
 */
public class MaxFrequency {

    public static void main(String[] args) {
        int[] nums = new int[]{9930,9923,9983,9997,9934,9952,9945,9914,9985,9982,9970,9932,9985,9902,9975,9990,9922,9990,9994,9937,9996,9964,9943,9963,9911,9925,9935,9945,9933,9916,9930,9938,10000,9916,9911,9959,9957,9907,9913,9916,9993,9930,9975,9924,9988,9923,9910,9925,9977,9981,9927,9930,9927,9925,9923,9904,9928,9928,9986,9903,9985,9954,9938,9911,9952,9974,9926,9920,9972,9983,9973,9917,9995,9973,9977,9947,9936,9975,9954,9932,9964,9972,9935,9946,9966};
        int k = 3056;
        int f = new Faster(nums, k).maxFrequency();
//        int f = maxFrequency(nums, k);
        System.out.println(f);
    }

    /**
     *
     * 元素的 频数 是该元素在一个数组中出现的次数。
     * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
     * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        int ans = 1;
        // 暴力枚举法
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i), cnt = map.get(x);
            int p = k;
            if (i > 0) {
                for (int j = i - 1; j >= 0; j--) {
                    int y = list.get(j);
                    int diff = x - y;
                    if (p >= diff) {
                        int min = p / diff;
                        // 这里比较出现次数，当p/diff不够数组中出现的次数时，取实际次数min，即取数组或实际的最小次数
                        min = Math.min(map.get(y), min);
                        p -= min * diff;
                        cnt += min;
                    } else {
                        break;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }


    static class Faster {

        int[] nums;
        int k;

        int[] sums;
        int n;

        Faster(int[] nums, int k) {
            this.nums = nums;
            this.k = k;
        }

        /**
         * 排序+前缀和+二分法+滑动窗口
         *
         * 时间复杂度：排序的复杂度为 O(n\log{n})O(nlogn)；计算前缀和数组复杂度为 O(n)O(n)；check 函数的复杂度为 O(n)O(n)，因此二分复杂度为 O(n\log{n})O(nlogn)。整体复杂度为 O(n\log{n})O(nlogn)
         * 空间复杂度：O(n)O(n)
         *
         * @return
         */
        public int maxFrequency() {
            // 排序
            Arrays.sort(nums);

            sums = new int[nums.length + 1];
            n = nums.length;
            // 这里求前缀和，sums[i]表示从nums[0]到nums[i - 1]的和
            // sums[0]=0，sums[1]=nums[0]，sums[2] = num[0] + nums[1]
            for (int i = 1; i <= n; i++) {
                sums[i] = sums[i -1] + nums[i - 1];
            }

            int l = 0, r = n;
            while(l < r) {
                // 二分法
                int mid = l + r + 1 >> 1;
                // 滑动窗口
                // 理解：检查mid长度区间是否满足k能达到最大频数，如果能，左边界移动到中间；如果不能，说明区间太长了，将右边界-1缩小后，再检查
                // 最后的r就是最大频数
                if(check(mid)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return r;
        }

        /**
         * 检查是否满足，在mid长度区间，有数组能满足 sums mid长度的和 + k>=nums[r]*mid长度，达到mid次最大频数
         * 用滑动窗口进行检查
         * @param mid
         * @return
         */
        private boolean check(int mid) {
            // 随着l增大，固定大小的窗口l->l+mid-1随之移动
            for (int l = 0; l + mid - 1 < n ; l++) {
                int r = l + mid - 1;
                // 前缀和，计算l->l+mid之间的和
                int cur = sums[r + 1] - sums[l];
                // 当mid区间所有数都是nums[r]时的值
                int t = nums[r] * mid;
                // 前缀和 + k >= t时，说明可以满足
                if (t - cur <= k) {
                    return true;
                }
            }
            return false;
        }
    }


}

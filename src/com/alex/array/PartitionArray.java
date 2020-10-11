package com.alex.array;

/**
 * Created by caozhennan on 2020/10/11 11:24 上午.
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 *     每个数组中的元素不会超过 100
 *     数组的大小不会超过 200
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 */
public class PartitionArray {
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        } else if (max == target) {
            return true;
        }
        boolean[][] dp = new boolean[nums.length][target+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i-1][j];
                if (nums[i] <= j) {
                    dp[i][j] = dp[i][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[dp.length-1][target];
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 5, 11, 5};
        int[] nums = new int[]{1, 2, 3, 5};
        System.out.println(new PartitionArray().canPartition(nums));
    }
}

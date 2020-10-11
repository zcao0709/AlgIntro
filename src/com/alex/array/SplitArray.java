package com.alex.array;

import java.util.Arrays;

/**
 * Created by caozhennan on 2020/7/25 6:10 下午.
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 */
public class SplitArray {

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] line : dp) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        for (int[] line : dp) {
            for (int c : line) {
                System.out.print(c + ", ");
            }
            System.out.println();
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,2,5,10,8};
        System.out.println(new SplitArray().splitArray(nums, 2));
    }
}

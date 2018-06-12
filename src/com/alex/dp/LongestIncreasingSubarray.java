package com.alex.dp;

import java.util.Arrays;

/**
 * Created by caozhennan on 2018/6/11.
 */
public class LongestIncreasingSubarray {
    public static int[] longest(int[] arr) {
        int[] dp = dp(arr);
        int max = 0;
        int idx = -1;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
                idx = i;
            }
        }
        int[] ret = new int[max];
        ret[--max] = arr[idx];
        for (int i = idx; i > 0; i--) {
            if (dp[i] == dp[i-1] + 1) {
                ret[--max] = dp[i-1];
            }
        }
        return ret;
    }

    private static int[] dp(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];

        dp[0] = 1;
        ends[0] = arr[0];
        int right = 0;
        for (int i = 1; i < arr.length; i++) {
            int pos = Arrays.binarySearch(ends, 0, right+1, arr[i]);
            if (pos < 0) {
                pos = -(pos + 1);
                right = Math.max(right, pos);
                ends[pos] = arr[i];
            }
            dp[i] = pos + 1;
        }
        System.out.println("dp: " + Arrays.toString(dp));
        return dp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println(Arrays.toString(longest(arr)));
    }
}

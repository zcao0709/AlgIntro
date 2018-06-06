package com.alex.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by caozhennan on 2017/11/27.
 */
/*
给定数组arr，arr中所有的值都为正数且不重复。每个值代表一种货币，每种货币可以使用任意张，
再给定一个整数aim代表要找的钱数，求组成aim的最少货币数。
生成行数为arr.length，列数为ait+1的动态规划表dp，dp[i][j]的含义是：
可以任意使用arr[0..i]货币的情况下，组成j所需的最小张数。
 */
public class MoneyChange {

    public static int moneyChange(int[] unit, int aim) {
        if (unit == null || unit.length == 0 || aim < 0) {
            return -1;
        }
        int[][] dp = new int[unit.length][aim + 1];
        int max = Integer.MAX_VALUE;

        for (int j = 1; j <= aim; j++) {
            dp[0][j] = max;
            if (j - unit[0] >= 0 && dp[0][j - unit[0]] != max) {
                dp[0][j] = dp[0][j - unit[0]] + 1;
            }
        }
        for (int i = 1; i < unit.length; i++) {
            for (int j = 1; j <= aim; j++) {
                int left = max;
                if (j - unit[i] >= 0 && dp[i][j - unit[i]] != max) {
                    left = dp[i][j - unit[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        for (int i = 0; i < unit.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[unit.length - 1][aim] != max ? dp[unit.length - 1][aim] : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aim = sc.nextInt();
        int n = sc.nextInt();
        int[] unit = new int[n];
        for (int i = 0; i < n; i++) {
            unit[i] = sc.nextInt();
        }
        System.out.println(moneyChange(unit, aim));
    }
}

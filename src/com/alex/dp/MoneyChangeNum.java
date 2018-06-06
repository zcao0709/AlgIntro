package com.alex.dp;

import com.alex.common.ArrayOps;

import java.util.Scanner;

/**
 * Created by caozhennan on 2018/6/5.
 */
/*
给定数组arr，arr中所有的值都为整数且不重复。每个值代表一种面值的货币，
每种货币可以使用任意张，再给定一个整数aim代表要找的钱数，求换钱的方法有多少种。
测试数据1：
15
4
5 10 25 1
输出：
6
测试数据2：
0
4
5 10 25 1
输出：
1
 */
public class MoneyChangeNum {

    public static int moneyChangeNum(int[] unit, int aim) {
        if (unit == null || unit.length == 0 || aim == 0) {
            return 0;
        }
        int[][] dp = new int[unit.length][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = unit[0]; j < dp[0].length; j += unit[0]) {
            dp[0][j] = 1;
        }
        ArrayOps.print(dp);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int count = 0;
                for (int k = 0; j >= unit[i] * k; k++) {
                    count += dp[i - 1][j - unit[i] * k];
                }
//                int count = dp[i - 1][j];
//                count += j >= unit[i] ? dp[i][j - unit[i]] : 0;
                dp[i][j] = count;
            }
            ArrayOps.print(dp);
        }
        return dp[unit.length-1][aim];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aim = sc.nextInt();
        int n = sc.nextInt();
        int[] unit = new int[n];
        for (int i = 0; i < n; i++) {
            unit[i] = sc.nextInt();
        }
        System.out.println(moneyChangeNum(unit, aim));
    }
}

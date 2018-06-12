package com.alex.dp;

import com.alex.common.ArrayOps;

/**
 * Created by caozhennan on 2018/6/11.
 */
public class CommonChars {
    public static String max(String str1, String str2) {
        int[][] dp = dp(str1, str2);
        char[] ret = new char[dp[dp.length-1][dp[0].length-1]];
        int index = ret.length - 1;

        int i = dp.length - 1;
        int j = dp[0].length - 1;

        while (index >= 0) {
            if (i >= 1 && dp[i][j] == dp[i-1][j]) {
                i--;
            } else if (j >= 1 && dp[i][j] == dp[i][j-1]) {
                j--;
            } else {
                ret[index--] = str1.charAt(i);
                i--;
                j--;
            }
        }
        return new String(ret);
    }

    private static int[][] dp(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];

        if (str1.charAt(0) == str2.charAt(0)) {
            dp[0][0] = 1;
        }

        for (int j = 1; j < str2.length(); j++) {
            if (str1.charAt(0) == str2.charAt(j)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j-1];
            }
        }
        for (int i = 1; i < str1.length(); i++) {
            if (str2.charAt(0) == str1.charAt(i)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }
        ArrayOps.print(dp);
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                int max = Math.max(dp[i][j-1], dp[i-1][j]);
                if (str1.charAt(i) == str2.charAt(j)) {
                    max = Math.max(max, dp[i-1][j-1] + 1);
                }
                dp[i][j] = max;
            }
            ArrayOps.print(dp);
        }
        return dp;
    }

    public static void main(String[] args) {
        String str1 = "1a2c3d4b56";
        String str2 = "b1d23ca45b6a";
        System.out.println(max(str1, str2)); // 12c4b6
    }
}

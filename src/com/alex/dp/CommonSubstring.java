package com.alex.dp;

/**
 * Created by caozhennan on 2018/6/11.
 */
public class CommonSubstring {
    public static String dp(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        int max = Integer.MIN_VALUE;
        int maxX = -1;

        for (int j = 0; j < str2.length(); j++) {
            if (str1.charAt(0) == str2.charAt(j)) {
                dp[0][j] = 1;
                if (dp[0][j] > max) {
                    max = dp[0][j];
                    maxX = 0;
                }
            }
        }
        for (int i = 1; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
                if (dp[i][0] > max) {
                    max = dp[i][0];
                    maxX = i;
                }
            }
        }
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        maxX = i;
                    }
                }
            }
        }
        return str1.substring(maxX-max+1, maxX+1);
    }

    public static void main(String[] args) {
        String str1 = "1ab2345cd";
        String str2 = "12345ef";
        System.out.println(dp(str1, str2)); // 2345
    }
}

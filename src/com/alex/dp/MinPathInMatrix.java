package com.alex.dp;

/**
 * Created by caozhennan on 2020/7/23 9:25 上午.
 */
public class MinPathInMatrix {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[][] dp = grid.clone();
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] += dp[0][j-1];
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] += dp[i-1][0];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] += Math.min(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public int minPathSum2(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int j = 1; j < dp.length; j++) {
            dp[j] = dp[j-1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < grid[i].length; j++) {
                dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
            }
        }
        return dp[dp.length-1];
    }

    public int minPathSum3(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] dp = new int[Math.min(grid.length, grid[0].length)];
        boolean upDown = dp.length == grid[0].length;
        dp[0] = grid[0][0];

        for (int j = 1; j < dp.length; j++) {
            dp[j] = dp[j - 1];
            if (upDown) {
                dp[j] += grid[0][j];
            } else {
                dp[j] += grid[j][0];
            }
        }
        for (int i = 1; i < (upDown ? grid.length : grid[0].length); i++) {
            dp[0] += (upDown ? grid[i][0] : grid[0][i]);
            for (int j = 1; j < dp.length; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]);
                if (upDown) {
                    dp[j] += grid[i][j];
                } else {
                    dp[j] += grid[j][i];
                }
            }
        }
        return dp[dp.length-1];
    }
}

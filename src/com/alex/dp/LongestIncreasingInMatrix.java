package com.alex.dp;

import java.util.Arrays;

/**
 * Created by caozhennan on 2020/7/26 8:27 上午.
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 */
public class LongestIncreasingInMatrix {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, columns;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] memo = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int row, int column, int[][] memo) {
        if (memo[row][column] != 0) {
            return memo[row][column];
        }
        ++memo[row][column];
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newColumn = column + dir[1];
            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
                memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
            }
        }
        return memo[row][column];
    }

    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] dp1 = new int[matrix.length][matrix[0].length];
        for (int[] line : dp1) {
            Arrays.fill(line, 1);
        }
        int max = 0;
        while (true) {
            boolean changed = false;
            for (int i = 0; i < dp1.length; i++) {
                for (int j = 0; j < dp1[i].length; j++) {
                    if (i > 0 && matrix[i][j] > matrix[i - 1][j]) {
                        if (dp1[i][j] < dp1[i-1][j]+1) {
                            changed = true;
                            dp1[i][j] = dp1[i-1][j]+1;
                        }
                    }
                    if (j > 0 && matrix[i][j] > matrix[i][j - 1]) {
                        if (dp1[i][j] < dp1[i][j-1]+1) {
                            changed = true;
                            dp1[i][j] = dp1[i][j-1]+1;
                        }
                    }
                    max = Math.max(max, dp1[i][j]);
                }
            }
            for (int[] line : dp1) {
                System.out.println(Arrays.toString(line));
            }
            for (int i = dp1.length - 1; i >= 0; i--) {
                for (int j = dp1[i].length - 1; j >= 0; j--) {
                    if (j < dp1[i].length - 1 && matrix[i][j] > matrix[i][j + 1]) {
                        if (dp1[i][j] < dp1[i][j + 1] + 1) {
                            changed = true;
                            dp1[i][j] = dp1[i][j + 1] + 1;
                        }
                    }
                    if (i < dp1.length - 1 && matrix[i][j] > matrix[i + 1][j]) {
                        if (dp1[i][j] < dp1[i + 1][j] + 1) {
                            changed = true;
                            dp1[i][j] = dp1[i + 1][j] + 1;
                        }
                    }
                    max = Math.max(max, dp1[i][j]);
                }
            }
            System.out.println();
            for (int[] line : dp1) {
                System.out.println(Arrays.toString(line));
            }
            if (!changed) {
                break;
            }
        }
        for (int[] line : dp1) {
            System.out.println(Arrays.toString(line));
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {7, 8, 9},
                {9, 7, 6},
                {7, 2, 3},
//                {1, 2}
//                {9, 9, 4},
//                {6, 6, 8},
//                {2, 1, 1},
        };
        System.out.println(new LongestIncreasingInMatrix().longestIncreasingPath(matrix));
    }
}

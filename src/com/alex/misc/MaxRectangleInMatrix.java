package com.alex.misc;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by caozhennan on 2017/11/20.
 * from a given matrix, in which all values are either 0 or 1,
 * find the max matrix in which all values are 1, return its area (width * height)
 * e.g.
 * 1 1 1 0: return 3
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0: return 6
 */
public class MaxRectangleInMatrix {

    public static int maxRect(int[] heights) {
        int max = 0;

        Deque<Integer> hs = new LinkedList<>();
        for (int i = 0; i < heights.length; i++) {
            while (!hs.isEmpty()) {
                if (heights[hs.getLast()] > heights[i]) {
                    int h = heights[hs.removeLast()];
                    int l = hs.isEmpty() ? -1 : hs.getLast();
                    max = Math.max(max, h * (i - 1 - (l + 1) + 1));
                } else if (heights[hs.getLast()] == heights[i]) {
                    hs.removeLast();
                } else {
                    break;
                }
            }
            hs.addLast(i);
        }
        while (!hs.isEmpty()) {
            int r = hs.removeLast();
            int l = hs.isEmpty() ? -1 : hs.getLast();
            max = Math.max(max, heights[r] * (r - (l + 1) + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[][] matrix = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int[] heights = new int[y];
        int max = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                heights[j] = matrix[i][j] == 0 ? 0 : heights[j]+1;
            }
            max = Math.max(max, maxRect(heights));
        }
        System.out.println(max);
    }
}

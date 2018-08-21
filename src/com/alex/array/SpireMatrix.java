package com.alex.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by caozhennan on 2018/7/23.
 */
public class SpireMatrix {
    private static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point rightDown() {
            return new Point(x+1, y+1);
        }

        public Point leftDown() {
            return new Point(x+1, y-1);
        }

        public Point rightUp() {
            return new Point(x-1, y+1);
        }

        public Point leftUp() {
            return new Point(x-1, y-1);
        }
    }

    public static int[] spire(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return new int[0];
        }
        int[] ret = new int[matrix.length * matrix[0].length];
        int pos = 0;
        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, matrix[0].length-1);
        Point p2 = new Point(matrix.length-1, matrix[0].length-1);
        Point p3 = new Point(matrix.length-1, 0);
        while (pos < ret.length) {
            pos = circle(matrix, p0, p1, p2, p3, ret, pos);
            p0 = p0.rightDown();
            p1 = p1.leftDown();
            p2 = p2.leftUp();
            p3 = p3.rightUp();
        }
        return ret;
    }

    private static int circle(int[][] matrix, Point p0, Point p1, Point p2, Point p3, int[] ret, int pos) {
        // go right
        for (int i = p0.y; i <= p1.y; i++) {
            ret[pos++] = matrix[p0.x][i];
        }
        // go down
        for (int i = p1.x+1; i <= p2.x; i++) {
            ret[pos++] = matrix[i][p1.y];
        }
        // go left
        for (int i = p2.y-1; i >= p3.y; i--) {
            ret[pos++] = matrix[p2.x][i];
        }
        // go up
        for (int i = p3.x-1; i > p0.x; i--) {
            ret[pos++] = matrix[i][p3.y];
        }
        return pos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];
        int v = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = v++;
            }
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println(Arrays.toString(spire(matrix)));
    }
}

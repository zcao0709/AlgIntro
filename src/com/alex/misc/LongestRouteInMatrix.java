package com.alex.misc;

import java.util.*;

/**
 * Created by caozhennan on 2017/3/21.
 */
public class LongestRouteInMatrix {
    private static int maxrow;
    private static int maxcol;
    private static Point[][] m;
    private static class Point {
        int val;
        int row;
        int col;
        int route;  // longest route ending at this point
        int direction;  // 0: left, 1: right, 2: up, 3: down, -1: stop

        public Point(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
            this.route = 0;
            this.direction = -1;
        }
        public Point move(int direction) {
            switch (direction) {
                case 0:
                    return left();
                case 1:
                    return right();
                case 2:
                    return up();
                case 3:
                    return down();
            }
            return this;
        }
        public Point left() {
            if (col - 1 >= 0) {
                return m[row][col-1];
            } else {
                return null;
            }
        }
        public Point right() {
            if (col + 1 < maxcol) {
                return m[row][col+1];
            } else {
                return null;
            }
        }
        public Point up() {
            if (row - 1 >= 0) {
                return m[row-1][col];
            } else {
                return null;
            }
        }
        public Point down() {
            if (row + 1 < maxrow) {
                return m[row+1][col];
            } else {
                return null;
            }
        }
        @Override
        public String toString() {
            return "[" + row + "][" + col + "]" + val + "-" + route;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        maxrow = sc.nextInt();
        maxcol = sc.nextInt();
        m = new Point[maxrow][maxcol];
        for (int i = 0; i < maxrow; i++) {
            for (int j = 0; j < maxcol; j++) {
                m[i][j] = new Point(sc.nextInt(), i, j);
            }
        }
        Point max = null;
        for (int i = 0; i < maxrow; i++) {
            for (int j = 0; j < maxcol; j++) {
                int r = route(m[i][j]);
                if (max == null || max.route < r) {
                    max = m[i][j];
                }
            }
        }
        System.out.print(max.route + ": [ ");
        Deque<Point> routes = new LinkedList<>();
        while (true) {
            routes.addFirst(max);
            if (max.direction == -1) {
                break;
            } else {
                max = max.move(max.direction);
            }
        }
        for (Point p : routes) {
            System.out.print(p.val + " ");
        }
        System.out.println("]");
    }

    public static int route(Point point) {
        if (point.route > 0) {
            return point.route;
        }
        for (int d = 0; d < 4; d++) {
            Point p = point.move(d);
            if (p != null && p.val < point.val) {
                int r = route(p);
                if (point.route < r + 1) {
                    point.route = r + 1;
                    point.direction = d;
                }
            }
        }
        if (point.route == 0) {
            point.route = 1;
            point.direction = -1;
        }
        return point.route;
    }
}

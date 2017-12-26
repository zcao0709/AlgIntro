package com.alex.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by caozhennan on 2017/12/26.
 */
public class MiddleOfLine {

    public static Point middle(List<Point> ps, int total) {
        if (ps == null || ps.size() == 0) {
            return null;
        }
        if (total == 0) {
            return ps.get(0);
        }
        int mid = total / 2;
        for (int i = 0; i < ps.size(); i++) {
            Point p = ps.get(i);
            if (p.len < mid) {
                mid -= p.len;
                continue;
            }
            return p.find(mid, ps.get(i + 1));
        }
        return null;
    }

    private static class Point {
        int x;
        int y;
        int len;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.len = 0;
        }

        public int dist(Point p) {
            if (x == p.x) {
                len = Math.abs(y - p.y);
            } else {
                len = Math.abs(x - p.x);
            }
            return len;
        }

        public Point find(int dist, Point p) {
            System.out.println(dist + "/" + p);
            int nx = 0;
            int ny = 0;
            if (x == p.x) {
                nx = x;
                if (y > p.y) {
                    ny = y - dist;
                } else {
                    ny = y + dist;
                }
            } else {
                ny = y;
                if (x > p.x) {
                    nx = x - dist;
                } else {
                    nx = x + dist;
                }
            }
            return new Point(nx, ny);
        }

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Point> ps = new ArrayList<>(n);
        int total = 0;
        for (int i = 0; i < n; i++) {
            ps.add(new Point(sc.nextInt(), sc.nextInt()));
            if (i > 0) {
                int len = ps.get(i - 1).dist(ps.get(i));
                if (len == 0) {
                    ps.remove(i);
                } else {
                    total += len;
                }
            }
        }
        System.out.println(middle(ps, total));
    }
}

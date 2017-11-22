package com.alex.misc;

import java.util.*;

/**
 * Created by caozhennan on 2017/11/20.
 */
public class SparseMatrixMultiply {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        List<Point> m1 = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            m1.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        x = sc.nextInt();
        List<Point> m2 = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            m2.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        Map<Integer, List<Point>> aux = new HashMap<>();
        for (Point p : m2) {
            List<Point> ps = aux.get(p.x);
            if (ps == null) {
                ps = new LinkedList<>();
                aux.put(p.x, ps);
            }
            ps.add(p);
        }

        Map<Point, Integer> ret = new HashMap<>();
        for (Point p : m1) {
            List<Point> ps = aux.get(p.y);
            for (Point p2 : ps) {
                Point np = new Point(p.x, p2.y, 0);
                Integer v = ret.get(np);
                if (v == null) {
                    ret.put(np, p.v * p2.v);
                } else {
                    ret.put(np, p.v * p2.v + v);
                }
            }
        }
        ret.entrySet().forEach(v -> System.out.println(v.getKey().toString() + ":" + v.getValue()));
    }

    private static class Point {
        int x;
        int y;
        int v;

        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return p.x == x && p.y == y;
        }

        @Override
        public int hashCode() {
            return x + y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }
}

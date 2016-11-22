package com.alex.hackerrank;

import java.util.*;

/**
 * Created by alex on 16/11/21.
 */
// https://www.hackerrank.com/challenges/castle-on-the-grid
public class CastleGrid {
    private Point[][] matrix;
    private Point init;
    private Point target;

    public CastleGrid(int n) {
        matrix = new Point[n][n];
    }

    private static class Point {
        int x;
        int y;
        int dist;
        Point prev;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            dist = Integer.MAX_VALUE;
            prev = null;
        }
        @Override
        public boolean equals(Object o) {
            Point p = (Point)o;
            return (x == p.x) && (y == p.y);
        }
        @Override
        public String toString() {
            return dist + "";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(Arrays.toString(matrix[i])).append("\n");
        }
        return sb.toString();
    }

    public void addPoint(int x, int y) {
        matrix[x][y] = new Point(x, y);
    }

    public void setInit(int x, int y) {
        init = matrix[x][y];
        init.dist = 0;
    }

    public void setTarget(int x, int y) {
        target = matrix[x][y];
    }

    private Point goRight(Point p) {
        if (p.y < matrix.length-1 && matrix[p.x][p.y+1] != null) {
            return matrix[p.x][p.y+1];
        } else {
            return null;
        }
    }

    private Point goLeft(Point p) {
        if (p.y > 0 && matrix[p.x][p.y-1] != null) {
            return matrix[p.x][p.y-1];
        } else {
            return null;
        }
    }

    private Point goUp(Point p) {
        if (p.x > 0 && matrix[p.x-1][p.y] != null) {
            return matrix[p.x-1][p.y];
        } else {
            return null;
        }
    }

    private Point goDown(Point p) {
        if (p.x < matrix.length-1 && matrix[p.x+1][p.y] != null) {
            return matrix[p.x+1][p.y];
        } else {
            return null;
        }
    }

    public int move() {
        Queue<Point> passed = new LinkedList<>();
        passed.offer(init);
        while (!passed.isEmpty()) {
            Point p = passed.poll();
            Point q = goRight(p);
            while (q != null) {
                if (q.prev == null || q.dist > p.dist + 1) {
                    q.dist = p.dist + 1;
                    q.prev = p;
                    passed.offer(q);
                } else if (q.dist < p.dist + 1) {
                    break;
                }
                q = goRight(q);
            }
            q = goLeft(p);
            while (q != null) {
                if (q.prev == null || q.dist > p.dist + 1) {
                    q.dist = p.dist + 1;
                    q.prev = p;
                    passed.offer(q);
                } else if (q.dist < p.dist + 1) {
                    break;
                }
                q = goLeft(q);
            }
            q = goUp(p);
            while (q != null) {
                if (q.prev == null || q.dist > p.dist + 1) {
                    q.dist = p.dist + 1;
                    q.prev = p;
                    passed.offer(q);
                } else if (q.dist < p.dist + 1) {
                    break;
                }
                q = goUp(q);
            }
            q = goDown(p);
            while (q != null) {
                if (q.prev == null || q.dist > p.dist + 1) {
                    q.dist = p.dist + 1;
                    q.prev = p;
                    passed.offer(q);
                } else if (q.dist < p.dist + 1) {
                    break;
                }
                q = goDown(q);
            }
        }
        return target.dist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        CastleGrid cg = new CastleGrid(n);
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                if (c == '.') {
                    cg.addPoint(i, j);
                }
            }
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        cg.setInit(a, b);
        cg.setTarget(c, d);
        System.out.println(cg.move());
//        System.out.println(cg.toString());
    }
}

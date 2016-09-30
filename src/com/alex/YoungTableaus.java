package com.alex;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by AlexC on 2016/9/29.
 */
// Problem 6.3
public class YoungTableaus {
    private int[][] table;
    private int size;
    public static int MAX_VALUE = Integer.MAX_VALUE;

    public YoungTableaus(int length) {
        table = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                table[i][j] = MAX_VALUE;
            }
        }
        size = 0;
    }

    public void offer(int val) {
        Point p = max();
        if (get(p) < MAX_VALUE) {
            throw new RuntimeException("overflow");
        } else {
            set(val, p);
        }
        validateUpLeft(p);
        size++;
    }

    public int poll() {
        Point min = min();
        int ret = get(min);
        if (ret == MAX_VALUE) {
            throw new RuntimeException("downflow");
        }
        set(MAX_VALUE, min);
        swap(min, max());
        validateDownRight(min);
        size--;
        return ret;
    }

    public int peek() {
        return get(min());
    }

    public boolean contains(int val) {
        if (val < peek()) {
            return false;
        } else if (val > get(max())) {
            return false;
        }
        Point p = min();
        while (inRange(p.down()) && get(p.down()) < MAX_VALUE) {
            if (get(p) == val) {
                return true;
            }
            p = p.down();
        }
        while (true) {
            if (val == get(p)) {
                return true;
            } else if (val > get(p) && inRange(p.right())) {
                p = p.right();
            } else if (val < get(p) && inRange(p.up())) {
                p = p.up();
            } else {
                break;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(Arrays.toString(table[i])).append("\n");
        }
        return sb.toString();
    }

    private void validateUpLeft(Point p) {
        Point max;
        Point left = p.left();
        Point up = p.up();
        if (inRange(left) && get(left) > get(p)) {
            max = left;
        } else {
            max = p;
        }
        if (inRange(up) && get(up) > get(max)) {
            max = up;
        }
        if (max != p) {
            swap(max, p);
            validateUpLeft(max);
        }
    }

    private void validateDownRight(Point p) {
        Point min;
        Point right = p.right();
        Point down = p.down();
        if (inRange(right) && get(p) > get(right)) {
            min = right;
        } else {
            min = p;
        }
        if (inRange(down) && get(min) > get(down)) {
            min = down;
        }
        if (min != p) {
            swap(min, p);
            validateDownRight(min);
        }
    }

    private Point min() {
        return new Point(0, 0);
    }

    private Point max() {
        return new Point(table.length-1, table[0].length-1);
    }

    private void swap(Point p1, Point p2) {
        int tmp = get(p1);
        set(get(p2), p1);
        set(tmp, p2);
    }

    private boolean inRange(Point p) {
        return p.x < table.length && p.x >= 0 && p.y < table[0].length && p.y >= 0;
    }

    private void set(int v, Point p) {
        table[p.x][p.y] = v;
    }

    private int get(Point p) {
        return table[p.x][p.y];
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point left() {
            return new Point(x, y-1);
        }

        Point up() {
            return new Point(x-1, y);
        }

        Point down() {
            return new Point(x+1, y);
        }

        Point right() {
            return new Point(x, y+1);
        }
    }

    public static void main(String[] args) {
        int size;
        try (Scanner sc = new Scanner(System.in)) {
            size = sc.nextInt();
        }
        YoungTableaus yt = new YoungTableaus(size);
        System.out.println(yt.toString());
        int[] num = new int[] {9, 16, 3, 2, 4, 8, 5, 14, 12, 1, 20, 10, -5, -10, 18, -20};
        for (int n : num) {
            yt.offer(n);
        }
        System.out.println(yt.toString());
//        System.out.println(yt.poll());
//        System.out.println(yt.toString());
        System.out.println(yt.contains(6));
    }
}

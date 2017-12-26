package com.alex.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by caozhennan on 2017/12/26.
 */
public class Jishui {

    public static int jishui(int[] heights) {
        if (heights == null || heights.length <= 2) {
            return 0;
        }
        List<Column> cs = new ArrayList<>(heights.length);
        int lh = 0;
        for (int h : heights) {
            cs.add(new Column(h, lh));
            lh = Math.max(h, lh);
        }
        int rh = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            cs.get(i).rh = rh;
            rh = Math.max(rh, cs.get(i).h);
        }
        int area = 0;
        for (Column c : cs) {
            area += c.area();
        }
        return area;
    }

    private static class Column {
        private int h;
        private int lh;
        private int rh;

        public Column(int h, int lh) {
            this.h = h;
            this.lh = lh;
        }

        public int area() {
            int b = Math.min(lh, rh);
            return h >= b ? 0 : b - h;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] hs = new int[n];
        for (int i = 0; i < n; i++) {
            hs[i] = sc.nextInt();
        }
        System.out.println(jishui(hs));
    }
}

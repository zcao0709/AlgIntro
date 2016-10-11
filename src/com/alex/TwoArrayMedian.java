package com.alex;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/10/9.
 */
// Exercise 9.3.8
public class TwoArrayMedian {
    public static int twoArrayMedian(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new RuntimeException("different length");
        }
        return twoArrayMedian(a, 0, b, 0, a.length);
    }

    private static int twoArrayMedian(int[]a, int aStart, int[] b, int bStart, int length) {
        if (length == 1) {
            return Math.min(a[aStart], b[bStart]);
        }
        int m = (length % 2 == 0) ? length/2-1 : length/2;
        int i = m ;
        if (a[aStart+m] < b[bStart+m]) {
            return twoArrayMedian(a, aStart+length-i, b, bStart, i);
        } else {
            return twoArrayMedian(a, aStart, b, bStart+length-i, i);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 15, 16, 17};
        int[] b = new int[] {8, 9, 10, 11, 12, 13, 20};
        System.out.println(twoArrayMedian(a, b));
    }
}

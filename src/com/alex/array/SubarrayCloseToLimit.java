package com.alex.array;

import com.alex.common.ArrayOps;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by caozhennan on 2019-01-30 10:22.
 */
// programming pearls page85 problem 10
public class SubarrayCloseToLimit {
    public static int[] closeSub(int[] arr, int limit) {
        int[] ret = new int[]{-1, -1, Integer.MAX_VALUE};
        TreeMap<Integer, Integer> sum = new TreeMap<>();
        sum.put(0, -1);

        int sumSofar = 0;
        for (int i = 0; i < arr.length; i++) {
            sumSofar += arr[i];
            int diff = sumSofar - limit;
            Map.Entry<Integer, Integer> above = sum.ceilingEntry(diff);
            diff(ret, above, sumSofar, limit, i);
            Map.Entry<Integer, Integer> below = sum.lowerEntry(diff);
            diff(ret, below, sumSofar, limit, i);
            sum.put(sumSofar, i);
        }
        return ret;
    }

    private static void diff(int[] ret, Map.Entry<Integer, Integer> entry, int sumSofar, int limit, int i) {
        if (entry != null) {
            int d = Math.abs(sumSofar - entry.getKey() - limit);
            if (d < ret[2]) {
                int left = entry.getValue();
                ret[0] = left == -1 ? 0 : left;
                ret[1] = i;
                ret[2] = d;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayOps.inputIt();
        System.out.println(Arrays.toString(closeSub(arr, 0)));
    }
}

package com.alex.array;

import com.alex.common.ArrayOps;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by caozhennan on 2018/6/2.
 */
//  未排序数组中累加和小于或等于给定值的最长子数组长度。
public class MaxSubarrayWithSumLimit {

    public static int maxWithTree(int[] arr, int limit) {
        int pre;
        int sum = 0;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        m.put(0, -1);

        int ret = 0;
        for (int i = 0; i < arr.length; i++) {
            pre = sum;
            sum += arr[i];
            Map.Entry<Integer, Integer> entry = m.ceilingEntry(sum - limit);
            if (entry != null) {
                System.out.println(ret + ", " + i + ", " + entry.getValue());
                ret = Math.max(ret, (i - entry.getValue()));
            }
            int key = Math.max(sum, pre);
            if (!m.containsKey(key)) {
                m.put(key, i);
            }
        }
        return ret;
    }

    public static int maxWithArray(int[] arr, int limit) {
        int sum = 0;
        int[] brr = new int[arr.length + 1];
        brr[0] = sum;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            brr[i + 1] = Math.max(sum, brr[i]);
        }
        System.out.println(Arrays.toString(brr));

        int ret = 0;
        sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int pre = ceiling(brr, sum - limit, i+1);
            System.out.println(i + ", " + pre);
            ret = Math.max(ret, i - pre + 1);
        }
        return ret;
    }

    private static int ceiling(int[] arr, int val, int to) {
        int i = Arrays.binarySearch(arr, 0, to, val);
        if (i < 0) {
            return -(i + 1);
        } else {
            while (i > 0 && arr[i-1] == val) {
                i--;
            }
            return i;
        }
    }

    public static void main(String[] args) {
        int[] a = ArrayOps.inputIt();
        System.out.println(maxWithTree(a, 2));
    }
}

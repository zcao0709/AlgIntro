package com.alex.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by caozhennan on 2018/6/4.
 */
// 未排序数组中累加和为给定值的最长子数组长度。
public class MaxSubarrayWithSum {

    public static int max(int[] arr, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);

        int sum = 0;
        int ret = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer index = m.get(sum - target);
            if (index != null) {
                ret = Math.max(ret, i - index);
            }
            if (!m.containsKey(sum)) {
                m.put(sum, i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int size = sc.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println("init: " + Arrays.toString(arr));
            int target = sc.nextInt();
            System.out.println(max(arr, target));
        }
    }
}

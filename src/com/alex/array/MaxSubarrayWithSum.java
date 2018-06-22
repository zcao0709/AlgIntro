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

    public static int max2(int[] arr, long target) {

        if(arr == null || arr.length == 0 || target < 0) {
            return 0;
        }

        int len = arr.length;
        //System.out.println("len = " + len);

        int maxLenth = 0;
        long sumValue = 0;
        for(int i = 0; i < len; i++) {
            sumValue += arr[i];
        }
        if(sumValue == target) {
            return len;
        }

        long[] sumArr = new long[len];
        sumArr[0] = sumValue;
        int tmpsum = 0;
        for(int i = 1; i < len; i++) {
            tmpsum += arr[i-1];
            sumArr[i] = sumValue - tmpsum;
            if(sumArr[i] ==  target) {
                maxLenth = Math.max(maxLenth, len - i);
//                return maxLenth;
            }
        }

        System.out.println(Arrays.toString(sumArr));
        System.out.println("maxLenth = " + maxLenth);

        for(int j = len -1; j > 0; j--) {
            for(int i = 0; i < j; i++) {
                sumArr[i] -= sumArr[j];
                if(sumArr[i] == target) {
                    //System.out.println("j = " + j + ", i = " + i);
                    maxLenth = Math.max(maxLenth, j - i);
                    return maxLenth;
                }
            }

        }

        return maxLenth;
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
            System.out.println(max2(arr, target));
        }
    }
}

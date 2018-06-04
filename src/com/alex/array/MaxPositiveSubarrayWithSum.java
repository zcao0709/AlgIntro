package com.alex.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by caozhennan on 2018/6/4.
 */
// 未排序正数数组中累加和为给定值的最长子数组长度。
public class MaxPositiveSubarrayWithSum {

    public static int max(int[] arr, int target) {
        int sum = 0;
        int ret = 0;
        int pre = -1;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == target) {
                ret = Math.max(ret, i - pre);
            } else if (sum > target) {
                pre++;
                sum -= arr[pre];
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

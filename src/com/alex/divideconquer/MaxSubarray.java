package com.alex.divideconquer;

import java.util.Arrays;

/**
 * Created by AlexC on 2016/9/26.
 */
// Section 4.1
public class MaxSubarray {
    public static long[] maxSubarrayDC(int[] array) {
        return maxSubarrayDC(array, 0, array.length-1);
    }

    private static long[] maxSubarrayDC(int[] array, int left, int right) {
        if (left == right) {
            return new long[] {left, left, array[left]};
        }
        int mid = (left + right) >>> 1;
        long[] crossSum = crossMidSum(array, left, mid, right);
        long[] leftSum = maxSubarrayDC(array, left, mid);
        long[] rightSum = maxSubarrayDC(array, mid+1, right);
        if (leftSum[2] >= rightSum[2] && leftSum[2] >= crossSum[2]) {
            return leftSum;
        }
        if (rightSum[2] >= leftSum[2] && rightSum[2] >= crossSum[2]) {
            return rightSum;
        }
        return crossSum;
    }

    private static long[] crossMidSum(int[] array, int left, int mid, int right) {
        long sum = 0;
        long lmax = Long.MIN_VALUE;
        int lidx = -1;
        for (int i = mid; i >= left; i--) {
            sum += array[i];
            if (sum > lmax) {
                lmax = sum;
                lidx = i;
            }
        }
        sum = 0;
        long rmax = Long.MIN_VALUE;
        int ridx = -1;
        for (int i = mid+1; i <= right; i++) {
            sum += array[i];
            if (sum > rmax) {
                rmax = sum;
                ridx = i;
            }
        }
        return new long[] {lidx, ridx, lmax + rmax};
    }

    public static long maxSubarray(int[] array) {
        long curSum = 0;
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            curSum += array[i];
            if (curSum > maxSum) {
                maxSum = curSum;
            } else if (curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        System.out.println(Arrays.toString(maxSubarrayDC(array)));
        System.out.println(maxSubarray(array));
    }
}

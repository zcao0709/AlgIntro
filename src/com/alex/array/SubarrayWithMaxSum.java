package com.alex.array;

import java.util.Arrays;

/**
 * Created by caozhennan on 2018/6/30.
 */
public class SubarrayWithMaxSum {

    public static int[] max1(int[] arr) {
        int[] maxSofar = new int[3];
        maxSofar[0] = Integer.MIN_VALUE;
        maxSofar[1] = -1;
        maxSofar[2] = -1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum > maxSofar[0]) {
                    maxSofar[0] = sum;
                    maxSofar[1] = i;
                    maxSofar[2] = j;
                }
            }
        }
        return maxSofar;
    }

    public static int[] max2a(int[] arr) {
        int[] maxSofar = new int[3];
        maxSofar[0] = Integer.MIN_VALUE;
        maxSofar[1] = -1;
        maxSofar[2] = -1;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSofar[0]) {
                    maxSofar[0] = sum;
                    maxSofar[1] = i;
                    maxSofar[2] = j;
                }
            }
        }
        return maxSofar;
    }

    public static int[] max2b(int[] arr) {
        int[] maxSofar = new int[3];
        maxSofar[0] = Integer.MIN_VALUE;
        maxSofar[1] = -1;
        maxSofar[2] = -1;
        int[] partsum = new int[arr.length+1];
        partsum[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            partsum[i+1] = partsum[i] + arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = partsum[j+1] - partsum[i];
                if (sum > maxSofar[0]) {
                    maxSofar[0] = sum;
                    maxSofar[1] = i;
                    maxSofar[2] = j;
                }
            }
        }
        return maxSofar;
    }

    // divide and conquer
    public static int[] max3(int[] arr) {
        return max3(arr, 0, arr.length-1);
    }

    private static int[] max3(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{Integer.MIN_VALUE, -1, -1};
//            return new int[]{0, -1, -1};
        } else if (left == right) {
                return new int[]{arr[left], left, right};
//            }
        }
        int mid = (left + right) >>> 1;
        int[] lefthalf = max3(arr, left, mid);
        int[] righthalf = max3(arr, mid+1, right);

        int[] withmid = new int[]{Integer.MIN_VALUE, -1, -1};
//        int[] withmid = new int[]{0, -1, -1};
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= 0; i--) {
            sum += arr[i];
            if (sum >= leftSum) {
                leftSum = sum;
                withmid[1] = i;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid+1; i <= right; i++) {
            sum += arr[i];
            if (sum >= rightSum) {
                rightSum = sum;
                withmid[2] = i;
            }
        }
        withmid[0] = leftSum + rightSum;
        if (withmid[0] > lefthalf[0] && withmid[0] > righthalf[0]) {
            return withmid;
        } else if (lefthalf[0] > righthalf[0]) {
            return lefthalf;
        } else {
            return righthalf;
        }
    }

    // dp
    public static int[] max4(int[] arr) {
        int[] maxSofar = new int[]{Integer.MIN_VALUE, -1, -1};
        int[] maxEndingHere = new int[]{0, -1, -1};

        for (int i = 0; i < arr.length; i++) {
            int sum = maxEndingHere[0] + arr[i];
            if (sum >= arr[i]) {
                maxEndingHere[0] = sum;
                maxEndingHere[2] = i;
            } else {
                maxEndingHere[0] = arr[i];
                maxEndingHere[1] = i;
                maxEndingHere[2] = i;
            }
            if (maxEndingHere[0] > maxSofar[0]) {
                maxSofar[0] = maxEndingHere[0];
                maxSofar[1] = maxEndingHere[1];
                maxSofar[2] = maxEndingHere[2];
            }
        }
        return maxSofar;
    }

    public static void main(String[] args) {
        int[] arr = {-31, -41, -59, -26, -53, -58, -97, -93, -23, -84};
//        int[] arr = {-1, -41, -9, 26, -53, -8, -7, -93, -23, 84};
        System.out.println(Arrays.toString(max3(arr)));
    }
}

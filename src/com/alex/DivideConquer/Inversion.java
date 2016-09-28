package com.alex.DivideConquer;

import java.util.Arrays;

/**
 * Created by AlexC on 2016/9/28.
 */
// Section 2.4
public class Inversion {
    public static int count(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int[] aux = arr.clone();
        return count(arr, aux, 0, arr.length-1);
    }

    private static int count(int[] dst, int[] src, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >>> 1;
        int c = count(src, dst, left, mid) + count(src, dst, mid+1, right);
        if (src[mid] <= src[mid+1]) {
            System.arraycopy(src, left, dst, left, right-left+1);
            return c;
        }
        for (int i = left, j = left, k = mid+1; i <= right; i++) {
            if (k > right || (j <= mid && src[j] <= src[k])) {
                dst[i] = src[j++];
            } else {
                dst[i] = src[k++];
                c += (mid - j + 1);
            }
        }
        return c;
    }

    public static void main(String[] args) {
//        int[] array = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
        int[] array = new int[] {2, 3, 8, 6, 1};
        System.out.println(count(array));
        System.out.println(Arrays.toString(array));
    }
}

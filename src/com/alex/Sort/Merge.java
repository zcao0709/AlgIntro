package com.alex.Sort;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Merge {
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] aux = arr.clone();
        sort(arr, aux, 0, arr.length-1);
    }

    private static void sort(int[] dst, int[] src, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >>> 1;
        sort(src, dst, left, mid);
        sort(src, dst, mid+1, right);
        if (src[mid] <= src[mid+1]) {
            System.arraycopy(src, left, dst, left, right-left+1);
            return;
        }
        for (int i = left, j = left, k = mid+1; i <= right; i++) {
            if (k > right || (j <= mid && src[j] <= src[k])) {
                dst[i] = src[j++];
            } else {
                dst[i] = src[k++];
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

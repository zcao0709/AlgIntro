package com.alex.Sort;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/28.
 */
public class Quick {
    public static void sort(int[] array) {
        sort(array, 0, array.length-1);
    }

    private static void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(array, left, right);
        sort(array, left, p - 1);
        sort(array, p + 1, right);
    }

    private static int partition(int[] a, int left, int right) {
        int key = a[right];
        int j = left - 1;
        for (int i = left; i < right; i++) {
            if (a[i] <= key) {
                ++j;
                swap(a, i, j);
            }
        }
        ++j;
        swap(a, j, right);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

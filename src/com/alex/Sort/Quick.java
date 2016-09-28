package com.alex.Sort;

import com.alex.Common.ArrayOps;

import java.util.Arrays;

/**
 * Created by AlexC on 2016/9/28.
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
                ArrayOps.swap(a, i, j);
            }
        }
        ++j;
        ArrayOps.swap(a, j, right);
        return j;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

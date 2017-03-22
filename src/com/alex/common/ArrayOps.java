package com.alex.common;

/**
 * Created by AlexC on 2016/9/28.
 */
public class ArrayOps {
    public static void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

package com.alex.sort;

import java.util.Arrays;

/**
 * Created by AlexC on 2016/9/26.
 */
// Section 2.1
public class Insertion {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    public static void sort(int[] arr, int left, int right) {
        if (arr == null || right-left <= 1) {
            return;
        }
        for (int i = left+1; i < right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

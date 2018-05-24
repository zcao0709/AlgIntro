package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;

/**
 * Created by AlexC on 2016/9/28.
 */
// Chapter 7
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
//        int[] array = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
        int[] array = ArrayOps.randomIt();
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] arr, int from, int to) {
        int len = to - from + 1;
        if (len <= 1) {
            return;
        }
        if (len == 2) {
            if (arr[from] > arr[to]) {
                ArrayOps.swap(arr, from, to);
            }
            return;
        }

        int position = to;
        int i = from; // i is location of the first bigger than base
        boolean hasBigNums = false;
        for (int j = from; j < to; j++) {
            if(arr[j] >= arr[position]) {
                hasBigNums = true;
                continue;
            }
            else {
                if(hasBigNums) {
                    ArrayOps.swap(arr, i, j); // swap the smaller element with the first bigger element
                }
                i++;
            }
        }

        // swap base with the first bigger element, so that base is in correct position
        ArrayOps.swap(arr, i, position);

        System.out.println(from + "-" + to + "/" + arr[i] + ":" + Arrays.toString(arr));

        int newPosition = i;
        if(newPosition == from || newPosition == to) {
            return;
        }

        quickSort(arr, from, newPosition - 1);

        quickSort(arr, newPosition + 1, to);
    }
}

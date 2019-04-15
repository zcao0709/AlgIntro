package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;
import java.util.Random;

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
//        int p = partitionBasic(array, left, right);
        int p = partitionBasic(array, left, right);
        sort(array, left, p - 1);
        sort(array, p + 1, right);
    }

    // algorithms introduction
    private static int partitionBasic(int[] a, int left, int right) {
        int key = a[right];
        int j = left - 1;
        for (int i = left; i < right; i++) {
            // invariant: arr[left...j] <= target && arr[j+1...right] > target
            if (a[i] <= key) {
                ++j;
                ArrayOps.swap(a, i, j);
            }
        }
        ++j;
        ArrayOps.swap(a, j, right);
        return j;
    }

    // programming pearls P119
    private static int partitionLomuto(int[] a, int left, int right) {
        int key = a[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            // invariant: arr[left...j] < target && arr[j+1...right] >= target
            if (a[i] < key) {
                ++j;
                ArrayOps.swap(a, i, j);
            }
        }
        ArrayOps.swap(a, j, left);
        return j;
    }

    // programming pearls P120
    private static int partition2(int[] a, int left, int right) {
        ArrayOps.swap(a, left, left + new Random().nextInt(right-left+1));
        int key = a[left];
        int i = left;
        int j = right + 1;
        while (true) {
            do {
                i++;
            } while (i <= right && a[i] < key);
            do {
                j--;
            } while (a[j] > key);
            if (i > j) {
                break;
            }
            ArrayOps.swap(a, i, j);
        }
        ArrayOps.swap(a, j, left);
        return j;
    }

    // programming pearls P123 Problem11
    public static void sortFatPartition(int[] a, int left, int right) {
        if (left >= right)
            return;
        int key = a[left];
        int j = left;
        int k = left;
        for (int i = left+1; i <= right; i++) {
            // invariant: arr[left...j] < target && arr[j...k] == target && arr[k+1...right] > target
            if (a[i] < key) {
                ++j;
                ArrayOps.swap(a, i, j);
                ++k;
                if (k > j) {
                    ArrayOps.swap(a, i, k);
                }
            } else if (a[i] == key) {
                ++k;
                ArrayOps.swap(a, i, k);
            }
        }
        ArrayOps.swap(a, j, left);
        System.out.printf("%s, left=%d, j=%d, k=%d, right=%d\n", Arrays.toString(a), left, j, k, right);
        sortFatPartition(a, left, j-1);
        sortFatPartition(a, k+1, right);
    }

    public static void main(String[] args) {
        int[] array = new int[] {20, 7, 40, 9, 60, 11};
//        int[] array = new int[] {1, -1, 1, 1, 2, 1, 1};
//        int[] array = ArrayOps.randomIt();
        sort(array, 0, array.length-1);
//        sortFatPartition(array, 0, array.length-1);
        System.out.println("result: " + Arrays.toString(array));
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
            } else {
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

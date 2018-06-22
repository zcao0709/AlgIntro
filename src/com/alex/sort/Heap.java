package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by AlexC on 2016/9/28.
 */
// Chapter 6
public class Heap {
    public static void sort(int[] array) {
        buildMaxHeapByInsert(array);
        System.out.println(Arrays.toString(array));
        int size = array.length;
        while (size > 1) {
            ArrayOps.swap(array, 0, --size);
            shiftDownNR(array, 0, size);
        }
    }

    private static void buildMaxHeap(int[] a) {
        for (int i = a.length/2-1; i >= 0; i--) {
            shiftDownNR(a, i, a.length);
        }
    }

    private static void buildMaxHeapByInsert(int[] a) {
        for (int i = 1; i < a.length; i++) {
            shiftUp(a, i);
        }
    }

    private static void shiftUp(int[] a, int idx) {
        while (idx != 0) {
            int parent = (idx - 1) / 2;
            if (a[idx] > a[parent]) {
                ArrayOps.swap(a, idx, parent);
                idx = parent;
            } else {
                break;
            }
        }
    }

    private static void shiftDown(int[] a, int idx, int size) {
        int left = idx * 2 + 1;
        int right = idx * 2 + 2;
        int max;
        if (left < size && a[left] > a[idx]) {
            max = left;
        } else {
            max = idx;
        }
        if (right < size && a[right] > a[max]) {
            max = right;
        }
        if (max != idx) {
            ArrayOps.swap(a, max, idx);
            shiftDown(a, max, size);
        }
    }

    private static void shiftDownNR(int[] a, int idx, int size) {
        int left = idx * 2 + 1;
        int right = left + 1;
        int max;
        while (left < size) {
            if (left < size && a[left] > a[idx]) {
                max = left;
            } else {
                max = idx;
            }
            if (right < size && a[right] > a[max]) {
                max = right;
            }
            if (max == idx) {
                break;
            }
            ArrayOps.swap(a, max, idx);
            idx = max;
            left = idx * 2 + 1;
            right = left + 1;
        }
    }

    public static void main(String[] args) {
        int size;
        try (Scanner sc = new Scanner(System.in)) {
            size = sc.nextInt();
        }
        int[] array = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

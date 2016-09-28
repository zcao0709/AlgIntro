package com.alex.Sort;

import com.alex.Common.ArrayOps;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by AlexC on 2016/9/28.
 */
public class Heap {
    public static void sort(int[] array) {
        buildMaxHeap(array);
        int size = array.length;
        while (size > 1) {
            ArrayOps.swap(array, 0, --size);
            maxHeapifyNR(array, 0, size);
        }
    }

    private static void buildMaxHeap(int[] a) {
        for (int i = a.length/2-1; i >= 0; i--) {
            maxHeapifyNR(a, i, a.length);
        }
    }

    private static void maxHeapify(int[] a, int idx, int size) {
        int left = idx * 2 + 1;
        int right = idx * 2 + 2;
        int max = idx;
        if (left < size && a[left] > a[idx]) {
            max = left;
        }
        if (right < size && a[right] > a[max]) {
            max = right;
        }
        if (max != idx) {
            ArrayOps.swap(a, max, idx);
            maxHeapify(a, max, size);
        }
    }
    private static void maxHeapifyNR(int[] a, int idx, int size) {
        int left = idx * 2 + 1;
        int right = idx * 2 + 2;
        int max = idx;
        while (left < size) {
            if (left < size && a[left] > a[idx]) {
                max = left;
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
            right = idx * 2 + 2;
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

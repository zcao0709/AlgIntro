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

    // when a[0...n-1] is a heap, shiftUp make a[0...n] a heap
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

    // programming pearls P152
    private static void shiftDownNR(int[] a, int idx, int size) {
        while (true) {
            int c = idx * 2 + 1;
            if (c >= size) {
                break;
            }
            if (c + 1 < size) {
                if (a[c+1] > a[c]) {
                    c++;
                }
            }
            if (a[idx] >= a[c]) {
                break;
            }
            ArrayOps.swap(a, idx, c);
            idx = c;
        }
    }

    public static void main(String[] args) {
        int[] array = ArrayOps.randomIt();
        sort(array);
        System.out.println("result: " + Arrays.toString(array));
    }
}

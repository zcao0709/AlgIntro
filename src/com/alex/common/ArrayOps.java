package com.alex.common;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

    public static int[] randomIt() {
        int size;
        try (Scanner sc = new Scanner(System.in)) {
            size = sc.nextInt();
        }
        int[] arr = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt(100);
        }
        System.out.println("init: " + Arrays.toString(arr));
        return arr;
    }

    public static int[] randomDistinct(int k, int max) {
        int[] arr = new int[max];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            swap(arr, i, i + r.nextInt(arr.length - i));
        }
        int[] brr = new int[k];
        for (int i = 0; i < k; i++) {
            brr[i] = arr[i];
        }
        System.out.println("init: " + Arrays.toString(brr));
        return brr;
    }
}

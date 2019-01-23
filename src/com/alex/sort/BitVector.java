package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by caozhennan on 2018/5/27.
 */
public class BitVector {

    public static void sort(int[] arr, int k, int max) {
        com.alex.misc.BitVector bv = new com.alex.misc.BitVector(max);
        for (int i = 0; i < k; i++) {
            bv.set(arr[i]);
        }
        int j = 0;
        for (int i = 0; i <= max; i++) {
            if (bv.test(i)) {
                arr[j++] = i;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        ArrayOps.shuffle(arr);
        System.out.printf("init: %s\n", Arrays.toString(arr));
        sort(arr, n, n-1);
        System.out.printf("ret: %s\n", Arrays.toString(arr));
    }
}

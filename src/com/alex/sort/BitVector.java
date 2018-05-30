package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;

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
        for (int i = 0; i < max; i++) {
            if (bv.test(i)) {
                arr[j++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayOps.randomDistinct(10, 100);
        sort(arr, 10, 100);
        System.out.println(Arrays.toString(arr));
    }
}

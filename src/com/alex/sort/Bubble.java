package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;

/**
 * Created by caozhennan on 2018/5/18.
 */
public class Bubble {

    public static void sort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]) {
                    ArrayOps.swap(arr, j, j + 1);
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] array = ArrayOps.randomIt();
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}

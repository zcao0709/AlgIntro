package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;

public class Selection {

    public static void sort(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            for(int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    ArrayOps.swap(arr, i, j);
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] array = ArrayOps.randomIt();
        sort(array);
        System.out.println("result: " + Arrays.toString(array));
    }
}

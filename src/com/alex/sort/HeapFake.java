package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by caozhennan on 2018/5/10.
 */
public class HeapFake {

    public static void sort(int[] array) {
        ThreadLocal<Integer> t = new ThreadLocal<>();
        t.set(10);

        for (int i = 0; i < array.length; i++) {
            maxHeapify(array, array.length-i);
            System.out.printf("%2d:%s\n", i, Arrays.toString(array));
            ArrayOps.swap(array, 0, array.length-i-1);
        }
    }

    private static void maxHeapify(int[] a, int len) {
        for (int i = len/2; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = left + 1;
            int max = i;
            if (left < len && a[left] > a[max]) {
                max = left;
            }
            if (right < len && a[right] > a[max]) {
                max = right;
            }
            ArrayOps.swap(a, i, max);
        }
    }

    public static void main(String[] args) {
        int[] array = ArrayOps.randomIt();
        heapSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    public static void heapSort(int[] arr, int len) {
        if(len <= 1) {
            return;
        }
        // 构建大顶堆: 从左到右，从下到上！
        int h = (int) Math.floor(Math.sqrt(len)) - 1;

        for(; h >= 0; h--) {

            int parentPos = (int) (Math.pow(2, h) - 1);
            int parentCounts = (int) Math.pow(2, h);

            for(int n = 0; n < parentCounts; n++) {

                int leftPos  = 2*parentPos+1;

                if(leftPos > len-1) {
                    break;
                }

                if(arr[leftPos] > arr[parentPos]) {
                    int t = arr[parentPos];
                    arr[parentPos] = arr[leftPos];
                    arr[leftPos] = t;
                }

                int rightPos = 2*parentPos+2;

                if(rightPos > len-1) {
                    break;
                }

                if(arr[rightPos] > arr[parentPos]) {
                    int t = arr[parentPos];
                    arr[parentPos] = arr[rightPos];
                    arr[rightPos] = t;
                }

                parentPos++;
            }
        }
        System.out.println(len + ": " + Arrays.toString(arr));

        // 换位，把最大根值换到最后
        int t = arr[len-1];
        arr[len-1] = arr[0];
        arr[0] = t;

        //递归调用
        heapSort(arr,len-1);
    }
}

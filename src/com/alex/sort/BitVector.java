package com.alex.sort;

import com.alex.common.ArrayOps;

import java.util.*;

/**
 * Created by caozhennan on 2018/5/27.
 */
public class BitVector {

    public static void sort(int[] arr, int max, int dups) {
        com.alex.common.NBitVector bv = new com.alex.common.NBitVector(max, dups);
        for (int i = 0; i < arr.length; i++) {
            bv.incr(arr[i]);
        }
        int j = 0;
        for (int i = 0; i <= max; i++) {
            int c = bv.count(i);
            while (c > 0) {
                arr[j++] = i;
                c--;
            }
        }
    }

    public static void sort(int[] arr, int max) {
        com.alex.common.BitVector bv = new com.alex.common.BitVector(max);
        for (int i = 0; i < arr.length; i++) {
            bv.set(arr[i]);
        }
        int j = 0;
        for (int i = 0; i <= max; i++) {
            if (bv.test(i)) {
                arr[j++] = i;
            }
        }
    }

    public static int[] distinctArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] dupArray(int n, int dup) {
        int[] arr = new int[n*2];
        int i = 0;
        for (; i < n; i++) {
            arr[i] = i;
        }
        Random r = new Random();
        Set<Integer> duped = new HashSet<>();
        while (true) {
            int d = r.nextInt(dup);
            int j;
            do {
                j = r.nextInt(n);
            } while (duped.contains(j));
            duped.add(j);
            while (i < arr.length && d > 0) {
                arr[i++] = arr[j];
                d--;
            }
            if (i == arr.length) {
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = dupArray(n, 10);
        ArrayOps.shuffle(arr);

        System.out.printf("init: %s\n", Arrays.toString(arr));
        sort(arr, n-1, 10);
        System.out.printf("ret: %s\n", Arrays.toString(arr));
    }
}

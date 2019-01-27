package com.alex.array;

import com.alex.common.ArrayOps;

import java.util.Arrays;

/**
 * Created by caozhennan on 2019-01-26 22:56.
 */
public class BinaryFind {
    // programming pearls page11 problemA
    public static int findMissing(int[] arr, int nbits) {
        int[] zero = new int[arr.length];
        int[] ones = new int[arr.length];
        int[] in = arr;
        int n = arr.length;
        int missing = 0;
        for (int b = nbits; b > 0; b--) {
            int probe = 1 << (b - 1);
            int[] zo = probe(in, n, zero, ones, probe);
            int z = zo[0], o = zo[1];
            if (z <= o) {
                if (z == 0) {
                    break;
                }
                in = zero;
                n = z;
            } else {
                missing |= probe;
                if (o == 0) {
                    break;
                }
                in = ones;
                n = o;
            }
        }
        return missing;
    }

    // programming pearls page17 problem2
    public static int findDuplicate(int[] arr, int nbits) {
        int[] zero = new int[arr.length];
        int[] ones = new int[arr.length];
        int[] in = arr;
        int n = arr.length;
        for (int b = nbits; b >= 0; b--) {
            if (b == 0) {
                if (n == 1) {
                    throw new IllegalArgumentException("no duplicate");
                }
                return in[0];
            }
            int probe = 1 << (b - 1);
            int[] zo = probe(in, n, zero, ones, probe);
            if (zo[0] >= zo[1]) {
                in = zero;
                n = zo[0];
            } else {
                in = ones;
                n = zo[1];
            }
        }
        return 0;
    }

    private static int[] probe(int[] arr, int len, int[] zero, int[] ones, int probe) {
        int z = 0, o = 0;
        for (int i = 0; i < len; i++) {
            if ((arr[i] & probe) == 0) {
                zero[z++] = arr[i];
            } else {
                ones[o++] = arr[i];
            }
        }
        System.out.printf("zero: %d%s\n", z, Arrays.toString(zero));
        System.out.printf("one : %d%s\n", o, Arrays.toString(ones));
        return new int[]{z, o};
    }

    public static void main(String[] args) {
        int nbits = 3;
//        int[] arr = new int[(1<<nbits)-1];
//        int missing = 5;
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i;
//            if (i >= missing) {
//                arr[i]++;
//            }
//        }
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        ArrayOps.shuffle(arr);
        System.out.printf("init: %s\n", Arrays.toString(arr));
//        System.out.println(findMissing(arr, nbits));
        System.out.println(findDuplicate(arr, nbits));
    }
}

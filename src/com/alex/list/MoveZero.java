package com.alex.list;

import com.alex.common.ArrayOps;

import java.util.Arrays;

/**
 * Created by caozhennan on 2019/10/17 12:45 下午.
 */
public class MoveZero {

    public static void move(int[] arr) {
        int last = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (last == -1) {
                    last = i;
                }
            } else if (last >= 0) {
                ArrayOps.swap(arr, i, last);
                last++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 0, 2, 0, 3, 0, 4, 5};
        move(a);
        System.out.println(Arrays.toString(a));
    }
}

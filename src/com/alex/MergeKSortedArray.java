package com.alex;

import com.alex.Common.ArrayOps;
import com.alex.Sort.Insertion;
import com.alex.Sort.Merge;

import java.util.*;

/**
 * Created by Administrator on 2016/9/28.
 */
public class MergeKSortedArray {
    private static class ArrayElem {
        int value;
        int array;

        ArrayElem(int value, int array) {
            this.value = value;
            this.array = array;
        }
    }

    public static int[] merge(int[][] arrays) {
        int size = 0;
        for (int i = 0; i < arrays.length; i++) {
            size += arrays[i].length;
        }
        int[] ret = new int[size];
        PriorityQueue<ArrayElem> q = new PriorityQueue<>(new Comparator<ArrayElem>() {
            @Override
            public int compare(ArrayElem x, ArrayElem y) {
                return x.value - y.value;
            }
        });
        for (int i = 0; i < arrays.length; i++) {
            q.add(new ArrayElem(arrays[i][0], i));
        }
        int i = 0;
        int[] idx = new int[arrays.length];
        while (!q.isEmpty()) {
            ArrayElem ae = q.poll();
            ret[i++] = ae.value;
            idx[ae.array]++;
            if (idx[ae.array] < arrays[ae.array].length) {
                q.add(new ArrayElem(arrays[ae.array][idx[ae.array]], ae.array));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int size;
        try (Scanner sc = new Scanner(System.in)) {
            size = sc.nextInt();
        }
        int[][] arrays = new int[size][];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            int s = r.nextInt(20);
            arrays[i] = new int[s];
            for (int j = 0; j < s; j++) {
                arrays[i][j] = r.nextInt(100);
            }
        }
        for (int i = 0; i < size; i++) {
            Merge.sort(arrays[i]);
            System.out.println(Arrays.toString(arrays[i]));
        }
        System.out.println(Arrays.toString(merge(arrays)));
    }
}

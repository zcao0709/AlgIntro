package com.alex.misc;

import com.alex.common.ArrayOps;

import java.util.*;

/**
 * Created by caozhennan on 2019/10/28 3:19 下午.
 */
public class MaxAfterOneSwap {
    public static void swap(int[] arr) {
        NavigableMap<Integer, List<Integer>> stat = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> indexes = stat.computeIfAbsent(arr[i], k -> new LinkedList<>());
            indexes.add(i);
        }
//        if (!swap(arr, 0, stat)) {
//            ArrayOps.swap(arr, 0, 1);
//        }
        if (!swapNR(arr, stat)) {
            ArrayOps.swap(arr, 0, 1);
        }
    }

    private static boolean swapNR(int[] arr, NavigableMap<Integer, List<Integer>> stat) {
        for (int i = 0; i < arr.length; i++) {
            if (stat.isEmpty()) {
                return false;
            }
            Map.Entry<Integer, List<Integer>> max = stat.lastEntry();
            int index = max.getValue().remove(0);
            if (index == i) {
                if (max.getValue().isEmpty()) {
                    stat.remove(max.getKey());
                }
            } else {
                ArrayOps.swap(arr, i, index);
                return true;
            }
        }
        return false;
    }

    private static boolean swap(int[] arr, int start, NavigableMap<Integer, List<Integer>> stat) {
        if (!stat.isEmpty()) {
            Map.Entry<Integer, List<Integer>> max = stat.lastEntry();
            int index = max.getValue().remove(0);
            if (index == start) {
                if (max.getValue().isEmpty()) {
                    stat.remove(max.getKey());
                }
                return swap(arr, start + 1, stat);
            } else {
                ArrayOps.swap(arr, start, index);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {8, 8, 1, 2};
        swap(arr);
        System.out.println(Arrays.toString(arr));
    }
}

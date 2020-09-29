package com.alex.recursion;

import java.util.*;

/**
 * Created by caozhennan on 2019/10/15 7:07 下午.
 */
public class NToM {
    private static Set<List<Integer>> unique = new HashSet<>();

    public static void nToM(int n, int m) {
        Deque<Integer> ret = new LinkedList<>();
        int[] count = new int[1];
        nToM(ret, n, m, count);
        System.out.println(count[0]);
        System.out.println(unique.size());
        for (List<Integer> i : unique) {
            System.out.println(i);
        }
    }

    private static void nToM(Deque<Integer> mid, int n, int m, int[] count) {
//        if (n == 0) {
//            for (; m > 1; m--) {
//                mid.add(0);
//            }
//        }
        if (m == 1) {
            mid.add(n);
            System.out.println(mid);
            List<Integer> clone = new ArrayList<>(mid.size());
            clone.addAll(mid);
            Collections.sort(clone);
            unique.add(clone);
            count[0]++;
            return;
        }
        int size = mid.size();
        int mean = n % m == 0 ? n/m : (n/m)+1;
        int start = mid.isEmpty() ? n : Math.min(n, mid.getLast());
        for (int i = start; i >= mean; i--) {
//        for (int i = n; i >= 0; i--) { // this is for all results
            mid.add(i);
            nToM(mid, n-i, m-1, count);
            while (mid.size() > size) {
                mid.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        nToM(10, 3);
    }
}

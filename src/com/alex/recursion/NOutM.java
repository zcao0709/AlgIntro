package com.alex.recursion;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by caozhennan on 2018/5/16.
 */
public class NOutM {
    public static volatile String s = "";
    public static void nOutM(List<Integer> ms, int n) {
        Deque<Integer> ns = new LinkedList<>();
        nOutM2(ms, 0, ns, n);
    }

    // first edition
    public static void nOutM(List<Integer> ms, int start, Deque<Integer> ns, int n) {
        if (ns.size() == n) {
            System.out.println(Arrays.toString(ns.toArray(new Integer[0])));
            return;
        }
        for (int i = start; i < ms.size(); i++) {
            Integer e = ms.remove(i);
            ns.addLast(e);
            nOutM(ms, i, ns, n);
            ns.removeLast();
            ms.add(i, e);
        }
    }

    // optimized
    public static void nOutM2(List<Integer> ms, int start, Deque<Integer> ns, int n) {
        if (ns.size() == n - 1) {
            for (int i = start; i < ms.size(); i++) {
                ns.add(ms.get(i));
                System.out.println(Arrays.toString(ns.toArray(new Integer[0])));
                ns.remove(ns.size() - 1);
            }
        } else {
            for (int i = start; i <= (ms.size() - (n - ns.size())); i++) {
                ns.addLast(ms.get(i));
                nOutM(ms, i+1, ns, n);
                ns.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> ms = new LinkedList<>();
        ms.add(1);
        ms.add(2);
        ms.add(3);
        ms.add(4);
        ms.add(5);
        ms.add(6);
        ms.add(7);
        ms.add(8);
        nOutM(ms, 5);
    }
}

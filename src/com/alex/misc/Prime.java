package com.alex.misc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2019-02-14 22:57.
 */
// programming pearls2 page11
public class Prime {
    // private method, so I did not check when n < 2
    private static boolean is(int n, List<Integer> primes) {
//        System.out.printf("%d: %s\n", n, primes);
//        if (n < 2) {
//            return false;
//        }
        Iterator<Integer> it = primes.iterator();
        while (it.hasNext()) {
            int p = it.next();
            if (p * p > n) {
                break;
            }
            if (n % p == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean is(int n) {
        if (n < 2) {
            return false;
        }
        if (n % 2 == 0) {
            return n == 2;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> primes(int n) {
        List<Integer> ret = new LinkedList<>();
        if (n < 2) {
            return ret;
        }
        ret.add(2);
        for (int i = 3; i <= n; i++) {
            if (is(i, ret)) {
                ret.add(i);
            }
        }
        return ret;
    }

    public static List<Integer> primes2(int n) {
        List<Integer> ret = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (is(i)) {
                ret.add(i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        List<Integer> primes = primes(1000);
        System.out.printf("%d: %s\n", primes.size(), primes);
        primes = primes2(1000);
        System.out.printf("%d: %s\n", primes.size(), primes);
    }
}

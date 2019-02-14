package com.alex.misc;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2019-02-14 22:57.
 */
// programming pearls2 page11
public class Prime {
    private static boolean is(int n, List<Integer> primes) {
        System.out.printf("%d: %s\n", n, primes);
        for (int i = 0; ; i++) {
            int p = primes.get(i);
            if (p * p > n) {
                break;
            }
            if (n % p == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> primes(int n) {
        List<Integer> ret = new LinkedList<>();
        ret.add(2);
        for (int i = 3; i <= n; i++) {
            if (is(i, ret)) {
                ret.add(i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        List<Integer> primes = primes(1000);
        System.out.printf("%d: %s\n", primes.size(), primes);
    }
}

package com.alex.recursion;

public class Fibonacci {
    public static long fiboRecu(long n) {
        if (n < 2) {
            return n;
        } else {
            return fiboRecu(n-1) + fiboRecu(n-2);
        }
    }

    public static long fiboTailRecu(long n) {
        if (n < 2) {
            return n;
        } else {
            return fiboTailRecu(n, 2, 1, 0);
        }
    }

    private static long fiboTailRecu(long n, long m, long prev, long prevPrev) {
        if (m == n) {
            return prev + prevPrev;
        } else {
            return fiboTailRecu(n, m+1, prev+prevPrev, prev);
        }
    }

    public static long fibo(long n) {
        if (n < 2) {
            return n;
        } else {
            long prev = 1;
            long prevPrev = 0;
            for (int i = 2; i < n; i++) {
                long tmp = prev;
                prev += prevPrev;
                prevPrev = tmp;
            }
            return prev + prevPrev;
        }
    }

    public static void main(String[] args) {
        long n = 50;
        long s = System.currentTimeMillis();
        System.out.printf("%d: %dms\n", fiboRecu(n), System.currentTimeMillis()-s);
        s = System.currentTimeMillis();
        System.out.printf("%d: %dms\n", fiboTailRecu(n), System.currentTimeMillis()-s);
        s = System.currentTimeMillis();
        System.out.printf("%d: %dms\n", fibo(n), System.currentTimeMillis()-s);
    }
}

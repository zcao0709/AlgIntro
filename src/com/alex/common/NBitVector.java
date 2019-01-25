package com.alex.common;

import java.util.Arrays;

// programming pearls page8 problem6
public class NBitVector {
    private static final int UNIT = 64; // since long[] is used
    private static final int SHIFT = 6; // since 64 == 2^6
    private static final int BIT_MASK = 0x3F; // since 64 = 0x3F+1
    private static final int MAX_BITS4ONE = 4; // # of occurrence should be less than 16

    private long[] vector;
    private int nbits4One;
    private long mask;

    public NBitVector(int max, int dups) {
        nbits4One = nbits4One(dups);
        if (nbits4One > MAX_BITS4ONE) {
            throw new IllegalArgumentException();
        }
        if (nbits4One == 3) {
            nbits4One++;
        }
        int nbits = nbits4One * (max + 1); // [0, max]
        int nlong = (nbits - 1) / UNIT + 1;

        vector = new long[nlong];
        mask = 2;
        for (int i = 1; i < nbits4One; i++) {
            mask *= 2;
        }
        mask--;
    }

    public String toString() {
        return nbits4One + Arrays.toString(vector) + mask;
    }

    public void incr(int n) {
        valid(n);

        int c = countIn(n);
        if (c+1 > mask) {
            throw new IllegalArgumentException();
        }
        vector[vIndex(n)] += bIndex(n);
    }

    public void decr(int n) {
        valid(n);

        int c = countIn(n);
        if (c-1 < 0) {
            throw new IllegalArgumentException();
        }
        vector[vIndex(n)] -= bIndex(n);
    }

    public int count(int n) {
        valid(n);

        return countIn(n);
    }

    private int countIn(int n) {
        int of = bOffset(n);
        long bMask = mask << of;
        long c = (vector[vIndex(n)] & bMask) >>> of;
        return (int)c;
    }

    private void valid(int n) {
        if (n * nbits4One >= vector.length * UNIT || n < 0) {
            throw new IllegalArgumentException();
        }
    }

    private int vIndex(int n) {
        return n * nbits4One >> SHIFT;
    }

    private int bOffset(int n) {
        return (n * nbits4One) & BIT_MASK; // % 64
    }

    private long bIndex(int n) {
        return 1L << bOffset(n);
    }

    private static int nbits4One(int times) {
        int n = 0;
        while (times != 0) {
            n++;
            times = times >>> 1;
        }
        return n;
    }

    public static void main(String[] args) {
        NBitVector v = new NBitVector(100, 2);
        System.out.println(v.toString());
        System.out.println(v.vIndex(64));
        System.out.println(v.bIndex(1));

        v.incr(1);
        v.incr(1);
        v.incr(1);
        System.out.println(v.count(1));
        v.decr(1);
        System.out.println(v.count(1));
    }
}

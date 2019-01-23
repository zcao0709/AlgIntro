package com.alex.misc;

import com.alex.common.ArrayOps;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by caozhennan on 2018/5/26.
 */
public class BitVector {
    private static final int UNIT = 64; // since long[] is used
    private static final int SHIFT = 6; // since 64 == 2^6
    private static final int MASK = 0x3F; // since 64 = 0x3F+1
    private long[] vector;
    private int max;

    public BitVector(int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        }
        vector = new long[max / UNIT + 1];
        this.max = max;
    }

    public void set(int bit) {
        if (bit >= vector.length * UNIT || bit < 0) {
            throw new IllegalArgumentException();
        }
        vector[vIndex(bit)] |= bIndex(bit);
    }

    public void clear(int bit) {
        if (bit >= vector.length * UNIT || bit < 0) {
            throw new IllegalArgumentException();
        }
        vector[vIndex(bit)] &= ~bIndex(bit);
    }

    public boolean test(int bit) {
        if (bit >= vector.length * UNIT || bit < 0) {
            throw new IllegalArgumentException();
        }
        long v = vector[vIndex(bit)] & bIndex(bit);
        return v > 0 || v == Long.MIN_VALUE;
    }

    private int vIndex(int bit) {
        return bit >> SHIFT; // bit / 64
    }

    private long bIndex(int bit) {
        return 1L << (bit & MASK); // bit % 64
    }

    @Override
    public String toString() {
        return max + Arrays.toString(vector);
    }

    public static void main(String[] args) {
        BitVector bv = new BitVector(100);
        bv.set(0);
        bv.set(1);
        System.out.println(bv);
        System.out.println(bv.test(0));
        System.out.println(bv.test(1));
        bv.clear(0);
        System.out.println(bv);
        System.out.println(bv.test(0));
        System.out.println(bv.test(1));
    }
}

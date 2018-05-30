package com.alex.misc;

import java.util.Arrays;

/**
 * Created by caozhennan on 2018/5/26.
 */
public class BitVector {
    private static final int UNIT = 64;
    private static final int SHIFT = 6;
    private static final int MASK = 0x3F;
    private long[] vector;

    public BitVector(int bitSize) {
        if (bitSize < 0) {
            throw new IllegalArgumentException();
        }
        vector = new long[bitSize / UNIT + 1];
    }

    public void set(int bit) {
        if (bit >= vector.length * UNIT || bit < 0) {
            throw new IllegalArgumentException();
        }
        vector[bit >> SHIFT] |= (1L << (bit & MASK));
    }

    public void clear(int bit) {
        if (bit >= vector.length * UNIT || bit < 0) {
            throw new IllegalArgumentException();
        }
        vector[bit >> SHIFT] &= ~(1L << (bit & MASK));
    }

    public boolean test(int bit) {
        if (bit >= vector.length * UNIT || bit < 0) {
            throw new IllegalArgumentException();
        }
        return (vector[bit >> SHIFT] & (1L << (bit & MASK))) > 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    public static void main(String[] args) {
        BitVector bv = new BitVector(4);
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

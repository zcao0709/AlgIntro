package com.alex.misc;

/**
 * Created by caozhennan on 2019-02-12 22:54.
 */
public class CountBit1 {
    // programming pearls page98 problem7
    public static int count(int[] nums) {
        int c = 0;
        for (int n : nums) {
            c += count(n);
        }
        return c;
    }

    public static int count(int x) {
        int c = 0;
        while (x != 0) {
            x &= (x - 1);
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 1};
        System.out.println(count(nums));
    }
}

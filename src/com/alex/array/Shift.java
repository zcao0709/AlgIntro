package com.alex.array;

import com.alex.common.ArrayOps;
import com.alex.common.Math;

/**
 * Created by caozhennan on 2018/6/9.
 */
public class Shift {
    // programming pearls page14
    public static void juggleShiftAB2BA(char[] chars, int n) {
//        for (int i = 0; i < Math.gcd(chars.length, n); i++)
        int c = 0; // how many elements are swapped
        int i = 0; // index into char[]
        while (true) {
            char tmp = chars[i];
            int j = i;
            for (int k = next(j, n, chars.length); k != i; j = k, k = next(j, n, chars.length)) {
                System.out.println("move " + k + ":" + chars[k] + " to " + j + ":" + chars[j]);
                chars[j] = chars[k];
                c++;
                System.out.println(i + ":" + new String(chars));
            }
            chars[j] = tmp;
            c++;
            if (c == chars.length) {
                break;
            }
            System.out.println(i + ":" + new String(chars));
        }
    }

    private static int next(int curr, int step, int max) {
        int next = curr + step;
        if (next >= max) {
            next -= max;
        }
        return next;
    }

    // programming pearls page14
    public static void shiftAB2BA(char[] chars, int n) {
        ArrayOps.reverse(chars, 0, n);
        System.out.println(new String(chars));
        ArrayOps.reverse(chars, n, chars.length);
        System.out.println(new String(chars));
        ArrayOps.reverse(chars, 0, chars.length);
        System.out.println(new String(chars));
    }

    // programming pearls page17 problem5
    public static void shiftABC2CBA(char[] chars, int n, int m) {
        ArrayOps.reverse(chars, 0, n);
        System.out.println(new String(chars));
        ArrayOps.reverse(chars, n+m, chars.length);
        System.out.println(new String(chars));
        ArrayOps.reverse(chars, 0, chars.length);
        System.out.println(new String(chars));
        ArrayOps.reverse(chars, chars.length-n-m, chars.length-n);
        System.out.println(new String(chars));
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        char[] chars = str.toCharArray();
        System.out.printf("init: %s\n", new String(chars));
        shiftAB2BA(chars, 3);
        System.out.printf("ret: %s\n", new String(chars));
        str = "abcdefghijk";
        chars = str.toCharArray();
        System.out.printf("\ninit: %s\n", new String(chars));
        shiftABC2CBA(chars, 3, 4);
        System.out.printf("ret: %s\n", new String(chars));
    }
}

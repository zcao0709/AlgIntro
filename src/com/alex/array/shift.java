package com.alex.array;

import com.alex.common.ArrayOps;
import com.alex.common.Math;

/**
 * Created by caozhennan on 2018/6/9.
 */
public class shift {
    public static void shift1(char[] chars, int n) {
        for (int i = 0; i < Math.gcd(chars.length, n); i++) {
            char tmp = chars[i];
            int j = i;
            while (true) {
                int k = j + n;
                if (k >= chars.length) {
                    k -= chars.length;
                }
                if (k == i) {
                    break;
                }
                System.out.println("move " + k + ":" + chars[k] + " to " + j + ":" + chars[j]);
                chars[j] = chars[k];
                j = k;
            }
            chars[j] = tmp;
            System.out.println(i + ":" + new String(chars));
        }
    }

    public static void shift2(char[] chars, int n) {
        ArrayOps.reverse(chars, 0, n);
        System.out.println(new String(chars));
        ArrayOps.reverse(chars, n, chars.length);
        System.out.println(new String(chars));
        ArrayOps.reverse(chars, 0, chars.length);
        System.out.println(new String(chars));
    }

    public static void shift2(char[] chars, int n, int m) {
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
        String str = "abcdefghijk";
        char[] chars = str.toCharArray();
        shift2(chars, 3, 4);
        System.out.println(new String(chars));
    }
}

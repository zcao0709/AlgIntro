package com.alex.common;

/**
 * Created by caozhennan on 2018/6/11.
 */
public class Math {
    public static int gcd(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else if (a < b) {
                b -= a;
            }
        }
        return a;
    }
}

package com.alex.misc;

import java.util.Scanner;

/**
 * Created by caozhennan on 2017/4/5.
 */
public class DifferentBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(diffBits(a, b));
    }

    public static int diffBits(int a, int b) {
        int d = a ^ b;
        int c = 0;
        System.out.printf("%x\n", d);
        while (d > 0) {
            if (d % 2 != 0) {
                c++;
            }
            d = d >> 1;
            System.out.printf("%x\n", d);
        }
        return c;
    }
}

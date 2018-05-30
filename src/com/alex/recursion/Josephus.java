package com.alex.recursion;

import java.util.Scanner;

/**
 * Created by caozhennan on 2017/11/7.
 */
public class Josephus {

    // concrete mathematics
    public static int survive(int n) {
        if (n == 1) {
            return 1;
        }
        if (n % 2 == 0) {
            return 2 * survive(n / 2) - 1;
        } else {
            return 2 * survive(n / 2) + 1;
        }
    }

    public static int survive2(int n) {
        if (n == 1) {
            return 1;
        }
        int base = 2;
        while (base < n) {
            base *= 2;
        }
        base /= 2;
        return 2 * (n - base) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(survive2(n));
    }
}

package com.alex.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2016/11/18.
 */
// https://www.hackerrank.com/challenges/waiter
public class Waiter {
    public static void main(String[] args) {
        solution1();
    }

    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        List<Stack<Integer>> a = new ArrayList<>(2);
        a.add(new Stack<>());
        a.add(new Stack<>());
        int ia = 0;
        IntStream.range(0, n).forEach(i -> a.get(0).push(sc.nextInt()));

        List<Stack<Integer>> b = new ArrayList<>(q);
        IntStream.range(0, q).forEach(i -> b.add(new Stack<>()));

        int[] primes = new int[q];
        primes[0] = 2;
        IntStream.range(1, q).forEach(i -> primes[i] = nextPrime(primes[i-1]));

        for (int i = 0; i < q; i++) {
            int nextIa = ia == 0 ? 1 : 0;
            while (!a.get(ia).isEmpty()) {
                int p = a.get(ia).pop();
                if (p % primes[i] == 0) {
                    b.get(i).push(p);
                } else {
                    a.get(nextIa).push(p);
                }
            }
            ia = nextIa;
        }
        StringBuilder sb = new StringBuilder();
        b.stream().forEach(stack -> {
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append("\n");
            }
        });
        while (!a.get(ia).isEmpty()) {
            sb.append(a.get(ia).pop()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int nextPrime(int last) {
        if (last < 2) {
            return 2;
        } else if (last == 2) {
            return 3;
        }
        while (true) {
            last += 2;
            if (isPrime(last)) {
                return last;
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num == 2 || num == 3) {
            return true;
        }
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

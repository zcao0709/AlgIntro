package com.alex.hackerrank;

import java.util.*;

/**
 * Created by Administrator on 2016/11/7.
 */
// https://www.hackerrank.com/challenges/java-dequeue
public class Dequeue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();

        Map<Integer, Integer> s = new HashMap<>();
        int max = -1;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.addLast(num);
            Integer v = s.getOrDefault(num, 0);
            v = v + 1;
            s.put(num, v);
            if (deque.size() == m) {
                max = Math.max(max, s.size());
                Integer first = deque.removeFirst();
                int value = s.get(first);
                value--;
                if (value == 0) {
                    s.remove(first);
                }
            }
        }
        System.out.println(max);
    }
}

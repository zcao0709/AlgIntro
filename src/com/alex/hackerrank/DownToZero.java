package com.alex.hackerrank;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by alex on 2016/11/29.
 */
// https://www.hackerrank.com/challenges/down-to-zero-ii
public class DownToZero {
    private static class Down {
        int value;
        int count;
        public Down(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        IntStream.range(0, q).forEach(i -> {
            Set<Integer> memorize = new HashSet<>();
            Queue<Down> queue = new LinkedList<>();
            int n = sc.nextInt();
            int count = 0;
            queue.offer(new Down(n, 0));
            while (!queue.isEmpty()) {
                Down d = queue.poll();
                count = d.count;
                if (d.value <= 1) {
                    if (d.value == 1) {
                        count++;
                    }
                    break;
                }
                if (!memorize.contains(d.value - 1)) {
                    memorize.add(d.value - 1);
                    queue.offer(new Down(d.value-1, d.count+1));
                }
                IntStream.rangeClosed(1, (int)Math.floor(Math.sqrt(d.value))).filter(j -> d.value % j == 0).forEach(j -> {
                    int div = Math.max(j, d.value/j);
                    if (!memorize.contains(div)) {
                        memorize.add(div);
                        queue.offer(new Down(div, d.count+1));
                    }
                });
            }
            System.out.println(count);
        });
    }
}

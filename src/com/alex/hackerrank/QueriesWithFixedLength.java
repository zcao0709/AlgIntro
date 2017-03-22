package com.alex.hackerrank;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by caozhennan on 2017/1/18.
 */
// https://www.hackerrank.com/challenges/queries-with-fixed-length
public class QueriesWithFixedLength {
    private static class Elem {
        int value;
        int index;
        public Elem(int value, int index) {
            this.value = value;
            this.index = index;
        }
        @Override
        public String toString() {
            return value + "-" + index;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] num = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
            if (num[i] < min) {
                min = num[i];
            }
            if (num[i] > max) {
                max = num[i];
            }
        }
        for (int i = 0; i < q; i++) {
            int d = sc.nextInt();
            if (min == max) {
                System.out.println(min);
            } else {
                if (d == num.length) {
                    System.out.println(max);
                } else if (d == 1) {
                    System.out.println(min);
                } else {
                    System.out.println(query(num, d));
                }
            }
        }
    }

    private static int query(int[] num, int d) {
        Set<Integer> removedIndex = new HashSet<>();
//        Queue<Elem> queue = new PriorityQueue<>((a, b) -> {
//            return b.value - a.value;
//        });
        Comparator<Elem> comp = new Comparator<Elem>() {
            @Override
            public int compare(Elem a, Elem b) {
                return b.value - a.value;
            }
        };
        Queue<Elem> queue = new PriorityQueue<>(comp);
        IntStream.range(0, d).forEach(i -> {
            queue.offer(new Elem(num[i], i));
        });
        int min = queue.peek().value;
        for (int i = d; i < num.length; i++) {
            //System.out.println(Arrays.toString(queue.toArray(new Elem[0])));
            removedIndex.add(i - d);
            queue.offer(new Elem(num[i], i));
            Elem e = queue.peek();
            while (removedIndex.contains(e.index) && !queue.isEmpty()) {
                queue.poll();
                e = queue.peek();
            }
            if (e.value < min) {
                min = e.value;
            }
        }
        return min;
    }
}

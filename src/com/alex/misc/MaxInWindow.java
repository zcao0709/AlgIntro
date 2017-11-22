package com.alex.misc;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by caozhennan on 2017/11/20.
 */
public class MaxInWindow {

    public static int[] maxInWindow(int[] values, int window) {
        int[] ret = new int[values.length - window + 1];
        int j = 0;

        Deque<Integer> max = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            while (!max.isEmpty() && values[max.getLast()] < values[i]) {
                max.removeLast();
            }
            max.addLast(i);
            if (i >= window - 1) {
                ret[j++] = values[max.getFirst()];
            }
            if (max.getFirst() == i - window + 1) {
                max.removeFirst();
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int window = sc.nextInt();
        int size = sc.nextInt();

        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(maxInWindow(values, window)));
    }
}

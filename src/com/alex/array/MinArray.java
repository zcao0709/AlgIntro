package com.alex.array;

/**
 * Created by caozhennan on 2020/7/22 9:11 上午.
 */
public class MinArray {
    public int minArray2(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else if (numbers[mid] < numbers[high]) {
                high = mid;
            } else {
                high--;
            }
        }
        return numbers[low];
    }

    public int minArray(int[] numbers) {
        return minArray(numbers, 0, numbers.length - 1);
    }

    private int minArray(int[] n, int left, int right) {
        System.out.println(left + ", " + right);
        if (n[left] < n[right] || left >= right) {
            return n[left];
        }
        int mid = (left + right) >>> 1;
        if (n[mid] > n[right]) {
            return minArray(n, mid + 1, right);
        } else if (n[mid] < n[right]) {
            return minArray(n, left, mid);
        } else {
            return minArray(n, left, right-1);
        }
    }

    public static void main(String[] args) {
//        int[] ns = new int[]{2,2,2,0,1};
        int[] ns = new int[]{10,1,10,10,10};
        System.out.println(new MinArray().minArray(ns));
    }
}

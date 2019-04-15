package com.alex.sort;

import java.util.Arrays;

/**
 * Created by caozhennan on 2019-03-21 17:52.
 */
public class SortWithOddAndEven {

    private static boolean isOdd(int index) {
        return (index & 1) == 1;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {20, 7, 40, 9, 60, 11, 80, 13};
        // odd is always greater than even
        Arrays.sort(array, (a, b) -> {
            int x = a;
            int y = b;
            if (x == y) {
                return 0;
            }
            if (isOdd(x)) {
                if (isOdd(y)) {
                    return x - y;
                } else {
                    return 1;
                }
            } else {
                if (!isOdd(y)) {
                    return x - y;
                } else {
                    return -1;
                }
            }
        });
        System.out.println("result: " + Arrays.toString(array));
        int nextOdd = (array.length+1) / 2;
        int nextEven = 0;
        int stop = nextOdd;
        for (int i = 1; i < stop; i++) {
//            System.out.printf("result: %d-%d, %s\n", i, nextOdd, Arrays.toString(array));
            if (isOdd(i)) {
                Integer tmp = array[i];
                array[i] = array[nextOdd];
                array[nextOdd] = tmp;
                nextEven = nextOdd;
                nextOdd++;
            } else {
//                if (array[i] > array[nextOdd]) {
                    Integer tmp = array[i];
                    array[i] = array[nextEven];
                    array[nextEven] = tmp;
//                }
            }
        }
        System.out.println("result: " + Arrays.toString(array));
    }
}

package com.alex;

import com.alex.Sort.Insertion;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by AlexC on 2016/9/26.
 */
public class BFPRT {
    /**
     *
     * @param a
     * @param id
     * @return
     */
    public static int select(int[] a, int id) {
        if (id > a.length) {
            throw new RuntimeException("invalid argument");
        }
        int ret = select(a, 0, a.length, id);
        System.out.println(Arrays.toString(a));
        return ret;
    }

    /**
     * BFPRT算法实现：在传入的数组中，获得第id个序号的数值，序号从1开始。求数组a下标fromIndex到toIndex(exclusive)中的第id个数
     *
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param id
     * @return
     */
    public static int select(int[] a, int fromIndex, int toIndex, int id) {
        // 小于等于5个数，直接排序得到结果
        if (toIndex - fromIndex <= 5) {
            Insertion.sort(a, fromIndex, toIndex);
            return a[fromIndex + id - 1];
        }
        int t = fromIndex - 1; // 当前替换到前面的中位数的下标
        for (int st = fromIndex; st < toIndex; st += 5) { // 每5个进行处理
            int end = Math.min(st + 5, toIndex);
            Insertion.sort(a, st, end); // 5个数的排序
            t++;
            swap(a, t, (st + end - 1)>>>1); // 将中位数替换到数组前面，便于递归求取中位数的中位数
        }
        int pivotId = (fromIndex + t) >>> 1; // l到t的中位数的下标，作为主元的下标
        select(a, fromIndex, t + 1, pivotId - fromIndex + 1);// 不关心中位数的值，保证中位数在正确的位置
        int m = partition(a, fromIndex, toIndex, pivotId);
        int cur = m - fromIndex + 1;
        if (id == cur)
            return a[m]; // 刚好是第id个数
        else if (id < cur)
            return select(a, fromIndex, m, id);// 第id个数在左边20
        else
            return select(a, m + 1, toIndex, id - cur); // 第id个数在右边
    }

    private static void swap(int[] a, int t, int i) {
        int tmp = a[t];
        a[t] = a[i];
        a[i] = tmp;
    }

    // 对数组a下标从l到r的元素进行划分
    private static int partition(int[] a, int l, int r, int pivotId) {
        // 以pivotId所在元素为划分主元
        swap(a, pivotId, --r);
        int j = l - 1; // 左边数字最右的下标
        for (int i = l; i < r; i++)
            if (a[i] <= a[r])
                swap(a, ++j, i);
        swap(a, ++j, r);
        return j;
    }

    public static void main(String[] args) {
        int size;
        try (Scanner sc = new Scanner(System.in)) {
            size = sc.nextInt();
        }
        int[] array = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        System.out.println(select(array, 5));
        Insertion.sort(array);
        System.out.println(Arrays.toString(array));
    }
}

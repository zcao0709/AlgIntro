package com.alex.array;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/13 4:35 下午.
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] s = nums1;
        int[] l = nums2;
        if (s.length > l.length) {
            s = nums2;
            l = nums1;
        }
        Map<Integer, Integer> m = array2Map(l);
        List<Integer> ret = new ArrayList<>(s.length);
        for (int i : s) {
            int val = m.getOrDefault(i, 0);
            if (val > 0) {
                ret.add(i);
                m.put(i, val - 1);
            }
        }
        int[] r = new int[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            r[i] = ret.get(i);
        }
        return r;
    }

    private Map<Integer, Integer> array2Map(int[] num) {
        Map<Integer, Integer> ret = new HashMap<>();
        for (int i : num) {
            int val = ret.getOrDefault(i, 0);
            ret.put(i, val + 1);
        }
        return ret;
    }
}

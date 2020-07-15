package com.alex.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhennan on 2020/7/15 10:20 上午.
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 */
public class NumOfBST {
    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        cache.put(1, 1);
        cache.put(2, 2);

        return numTrees(n, cache);
    }

    private int numTrees(int n, Map<Integer, Integer> cache) {
        int ret = cache.getOrDefault(n, 0);
        if (ret == 0) {
            for (int i = 1; i <= n; i++) {
                ret += (numTrees(i-1, cache) * numTrees(n-i, cache));
//                ret++;
//                ret += numTrees(n - i, cache);
            }
            cache.put(n, ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new NumOfBST().numTrees(4));
    }
}
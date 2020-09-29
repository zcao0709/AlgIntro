package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.*;

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

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return null;
        }
        List<TreeNode> ret = new LinkedList<>();
        return generateTreesR(1, n);
    }

    private List<TreeNode> generateTreesR(int left, int right) {
        List<TreeNode> r = new LinkedList<>();
        if (left == right) {
            r.add(new TreeNode(left));
        } else if (left > right) {
            r.add(null);
        } else {
            for (int i = left; i <= right; i++) {
                List<TreeNode> lTree = generateTreesR(left, i - 1);
                List<TreeNode> rTree = generateTreesR(i + 1, right);
                for (TreeNode ln : lTree) {
                    for (TreeNode rn : rTree) {
                        TreeNode root = new TreeNode(i);
                        root.left = ln;
                        root.right = rn;
                        r.add(root);
                    }
                }
            }
        }
        return r;
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<StringBuilder> ret = sumNumbersR(root);
        int sum = 0;
        for (StringBuilder sb : ret) {
            sum += Integer.parseInt(sb.toString());
        }
        return sum;
    }

    private List<StringBuilder> sumNumbersR(TreeNode node) {
        if (node == null) {
            return new LinkedList<>();
        }
        List<StringBuilder> ret = sumNumbersR(node.left);
        ret.addAll(sumNumbersR(node.right));
        if (ret.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.val);
            ret.add(sb);
        } else {
            for (StringBuilder sb : ret) {
                sb.insert(0, node.val);
            }
        }
        return ret;
    }

    private int sumNumbersR2(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        sum *= 10;
        sum += node.val;
        return sumNumbersR2(node.left, sum) + sumNumbersR2(node.right, sum);
    }

    public static void main(String[] args) {
//        System.out.println(new NumOfBST().numTrees(4));
//        System.out.println(Arrays.toString(new NumOfBST().generateTrees(3).toArray(new TreeNode[0])));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(new NumOfBST().sumNumbers(root));
    }
}
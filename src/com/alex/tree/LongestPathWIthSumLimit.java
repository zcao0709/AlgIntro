package com.alex.tree;

import com.alex.common.BinaryTree;
import com.alex.common.TNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by caozhennan on 2018/6/3.
 */
// Find the max length of a path whose sum is a specified value in a binary tree.
public class LongestPathWIthSumLimit {

    public static int longest(TNode root, int sum) {
        Map<Integer, Integer> mid = new HashMap<>();
        mid.put(0, 0);
        int[] result = new int[1];
        result[0] = 0;

        longest(root, sum, 0, 1, mid, result);
        return result[0];
    }

    // pre-order
    public static void longest(TNode node, int target, int curr, int level, Map<Integer, Integer> mid, int[] result) {
        if (node == null) {
            return;
        }
        curr += node.value;
        if (mid.containsKey(curr - target)) {
            result[0] = Math.max(result[0], level - mid.get(curr - target));
        }
        if (!mid.containsKey(curr)) {
            mid.put(curr, level);
        }
        longest(node.left, target, curr, level+1, mid, result);
        longest(node.right, target, curr, level+1, mid, result);

        if (level == mid.get(curr)) {
            mid.remove(curr);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = BinaryTree.valueOf(17);
        TNode head = tree.root;
        BinTree.printByLevel(head);

        try (Scanner sc = new Scanner(System.in)) {
            int sum = sc.nextInt();
            System.out.println(longest(head, sum));
        }
    }
}

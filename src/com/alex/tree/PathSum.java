package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhennan on 2020/8/2 9:08 上午.
 */
public class PathSum {
    private int count = 0;

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        pathSumInOrder(root, 0, sum, cache);

        return count;
    }

    private void pathSumInOrder(TreeNode node, int currSum, int sum, HashMap<Integer, Integer> cache) {
        if (node == null) {
            return;
        }
        currSum += node.val;
        if (cache.containsKey(currSum - sum)) {
            count += cache.get(currSum - sum);
        }
        if (cache.containsKey(currSum)) {
            cache.computeIfPresent(currSum, (k, v) -> (v+1));
        } else {
            cache.put(currSum, 1);
        }
        System.out.println(cache);
        HashMap<Integer, Integer> left = new HashMap<>(cache);
        pathSumInOrder(node.left, currSum, sum, left);
        HashMap<Integer, Integer> right = new HashMap<>(cache);
        pathSumInOrder(node.right, currSum, sum, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);

        System.out.println(new PathSum().pathSum(root, 1));
    }
}

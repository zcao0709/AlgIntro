package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2020/7/17 2:05 下午.
 */
public class RootToLeafSum {
    int currSum = 0;
    List<Integer> currPath = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new LinkedList<>();
        if (root != null) {
            pathSum(root, sum, ret);
        }
        return ret;
    }

    private void pathSum(TreeNode node, int sum, List<List<Integer>> ret) {
        currSum += node.val;
        currPath.add(node.val);

        if (node.left == null && node.right == null) {
            if (currSum == sum) {
                List<Integer> one = new ArrayList<>(currPath.size());
                one.addAll(currPath);
                ret.add(one);
            }
        }
        if (node.left != null) {
            pathSum(node.left, sum, ret);
        }
        if (node.right != null) {
            pathSum(node.right, sum, ret);
        }
        currSum -= node.val;
        currPath.remove(currPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        List<List<Integer>> ret = new RootToLeafSum().pathSum(root, 22);
        for (List<Integer> r : ret) {
            System.out.println(Arrays.toString(r.toArray(new Integer[0])));
        }
    }
}

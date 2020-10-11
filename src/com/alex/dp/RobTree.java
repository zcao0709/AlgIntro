package com.alex.dp;

import com.alex.common.TreeNode;

/**
 * Created by caozhennan on 2020/7/20 2:53 下午.
 */
public class RobTree {
    public int rob(TreeNode root) {
        int[] ret = robR(root);
        return Math.max(ret[0], ret[1]);
    }

    private int[] robR(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        int[] left = robR(node.left);
        int[] right = robR(node.right);
        int[] ret = new int[]{node.val, 0};
        ret[0] += (left[1] + right[1]);
        ret[1] = Math.max(left[0] + right[0], left[1] + right[1]);
        ret[1] = Math.max(ret[1], left[0] + right[1]);
        ret[1] = Math.max(ret[1], left[1] + right[0]);

        return ret;
    }
}

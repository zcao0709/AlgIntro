package com.alex.common;

/**
 * Created by caozhennan on 2020/7/15 9:18 下午.
 * for LeeCode
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val + "/L:" + (left == null ? "null" : left.val) + "-R:" + (right == null ? "null" : right.val);
    }
}

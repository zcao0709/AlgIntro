package com.alex.common;

/**
 * Created by caozhennan on 2017/11/21.
 */
public class TNode {
    public int value;
    public TNode left;
    public TNode right;

    public TNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "/L:" + (left == null ? "null" : left.value) + "-R:" + (right == null ? "null" : right.value);
    }
}

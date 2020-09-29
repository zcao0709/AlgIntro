package com.alex.tree;

import com.alex.common.TNode;

/**
 * Created by caozhennan on 2020/7/13 5:13 下午.
 *
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 *
 * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 * 输入的二叉树至少有一个节点。
 */
public class InsertRow {
    public TNode addOneRow(TNode root, int v, int d) {
        if (d == 1) {
            TNode node = new TNode(v);
            node.left = root;
            return root;
        } else {
            preOrder(root, 1, v, d);
            return root;
        }
    }

    private void preOrder(TNode node, int depth, int v, int d) {
        if (node == null) {
            return;
        }
        if (depth + 1 == d) {
            TNode left = node.left;
            node.left = new TNode(v);
            node.left.left = left;

            TNode right = node.right;
            node.right = new TNode(v);
            node.right.right = right;

            return;
        }
        preOrder(node.left, depth+1, v, d);
        preOrder(node.right, depth+1, v, d);
    }
}

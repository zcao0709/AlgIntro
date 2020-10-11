package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by caozhennan on 2020/9/29 10:51 下午.
 *
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 */
public class MaxWidth {
    private static class TreeNodeWithIndex {
        TreeNode treeNode;
        int index;

        public TreeNodeWithIndex(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }
    private int width = 0;

    public int maxWidth(TreeNode root) {
        if (root != null) {
            Deque<TreeNodeWithIndex> layer = new LinkedList<>();
            layer.add(new TreeNodeWithIndex(root, 0));

            while (!layer.isEmpty()) {
                width = Math.max(width, layer.getLast().index - layer.getFirst().index + 1);

                Deque<TreeNodeWithIndex> next = new LinkedList<>();
                for (TreeNodeWithIndex node : layer) {
                    if (node.treeNode.left != null) {
                        next.add(new TreeNodeWithIndex(node.treeNode.left, node.index * 2));
                    }
                    if (node.treeNode.right != null) {
                        next.add(new TreeNodeWithIndex(node.treeNode.right, node.index * 2 + 1));
                    }
                }
                layer = next;
            }
        }
        return width;
    }
}

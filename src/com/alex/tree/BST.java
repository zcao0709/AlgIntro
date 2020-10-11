package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhennan on 2020/7/24 6:21 下午.
 */
public class BST {
    private Map<TreeNode, TreeNode> parent = new HashMap<>();
    private TreeNode keyNode;
    private TreeNode root;

    public TreeNode deleteNode(TreeNode root, int key) {
        this.root = root;
        find(root, key);
        if (keyNode == null) {
            return null;
        }
        if (keyNode.left == null) {
            transplant(keyNode, keyNode.right);
        } else if (keyNode.right == null) {
            transplant(keyNode, keyNode.left);
        } else {
            TreeNode c = lowestNode(keyNode.right);
            if (parent.get(c) != keyNode) {
                transplant(c, c.right);
                c.right = keyNode.right;
                parent.put(c.right, c);
            }
            transplant(keyNode, c);
            c.left = keyNode.left;
            parent.put(c.left, c);
        }
        keyNode.left = null;
        keyNode.right = null;
        return this.root;
    }

    private TreeNode lowestNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void find(TreeNode node, int key) {
        if (node == null) {
            return;
        }
        if (node.val == key) {
            keyNode = node;
        }
        if (node.left != null) {
            parent.put(node.left, node);
            find(node.left, key);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            find(node.right, key);
        }
    }

    private void transplant(TreeNode oldChild, TreeNode newChild) {
        if (oldChild == null) {
            throw new RuntimeException("internal error: no such node");
        }
        TreeNode p = parent.get(oldChild);
        if (p == null) {
            root = newChild;
        } else {
            if (oldChild == p.left) {
                p.left = newChild;
            } else {
                p.right = newChild;
            }
            parent.remove(oldChild);
        }
        if (newChild != null) {
            parent.put(newChild, p);
        }
    }
}

package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by caozhennan on 2020/7/17 11:39 上午.
 */
public class CommonAncestor {
    private Map<TreeNode, Integer> cache = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || (p == null && q == null)) {
            return null;
        } else if (p == null) {
            return q;
        } else if (q == null) {
            return p;
        } else if (p == q) {
            return p;
        }

        while (root != null) {
            int left = searchByDFS(root.left, p, q);
            if (left == 2) {
                root = root.left;
                continue;
            }
            int right = searchByDFS(root.right, p, q);
            if (right == 2) {
                root = root.right;
                continue;
            }
            break;
        }
        return root;
    }

    private int searchByDFS(TreeNode node, TreeNode p, TreeNode q) {
        int ret = 0;
        if (node != null) {
            Integer r = cache.get(node);
            if (r != null) {
                return r;
            }
            if (node == p || node == q) {
                ret++;
            }
            ret += searchByDFS(node.left, p, q);
            ret += searchByDFS(node.right, p, q);
            cache.put(node, ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        System.out.println(new CommonAncestor().lowestCommonAncestor(root, root.right, root.left).val);
    }
}

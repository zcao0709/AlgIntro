package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/15 9:20 下午.
 */
public class CommonAncestorOfDeepestLeaves {
    HashMap<TreeNode, Integer> height = new HashMap<>();

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        heightByPostOrder(root);
        System.out.println(height);
        int left = root.left == null ? 0 : height.get(root.left);
        int right = root.right == null ? 0 : height.get(root.right);
        while (left != right) {
            if (left > right) {
                root = root.left;
            } else {
                root = root.right;
            }
            left = root.left == null ? 0 : height.get(root.left);
            right = root.right == null ? 0 : height.get(root.right);
        }
        return root;
    }

    public TreeNode lcaDeepestLeaves2(TreeNode root) {
        if (root == null) {
            return null;
        }
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        List<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (true) {
            List<TreeNode> tmp = new LinkedList<>();

            for (TreeNode node : queue) {
                if (node.left != null) {
                    tmp.add(node.left);
                    parent.put(node.left, node);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                    parent.put(node.right, node);
                }
            }
            if (tmp.isEmpty()) {
                break;
            } else {
                queue = tmp;
            }
        }
        System.out.println(Arrays.toString(queue.toArray(new TreeNode[0])));
        System.out.println(parent);
        while (queue.size() != 1) {
            Set<TreeNode> set = new HashSet<>();
            while (!queue.isEmpty()) {
                set.add(parent.get(queue.remove(0)));
            }
            queue.addAll(set);
        }
        return queue.get(0);
    }

    private int heightByPostOrder(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int h = Math.max(heightByPostOrder(node.left), heightByPostOrder(node.right)) + 1;
        height.put(node, h);
        return h;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left.right = new TreeNode(6);
        root.left.right.right = new TreeNode(5);

        System.out.println(new CommonAncestorOfDeepestLeaves().lcaDeepestLeaves(root).val);
    }
}

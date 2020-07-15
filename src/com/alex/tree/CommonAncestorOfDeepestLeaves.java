package com.alex.tree;

import com.alex.common.TreeNode;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/15 9:20 下午.
 */
public class CommonAncestorOfDeepestLeaves {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return root;
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

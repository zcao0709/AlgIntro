package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhennan on 2020/7/24 5:24 下午.
 */
public class PostInOrder2Tree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private TreeNode buildTree(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
        if (pStart > pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pEnd]);
        int rootIn = inMap.get(root.val);
        int leftChildNum = rootIn - iStart;
        root.left = buildTree(inorder, iStart, rootIn-1, postorder, pStart, pStart+leftChildNum-1);
        root.right = buildTree(inorder, rootIn+1, iEnd, postorder, pStart+leftChildNum, pEnd);

        return root;
    }
    Map<Integer, Integer> inMap = new HashMap<>();
}
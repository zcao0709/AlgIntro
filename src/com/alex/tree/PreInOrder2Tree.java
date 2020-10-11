package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caozhennan on 2020/7/17 4:03 下午.
 */
public class PreInOrder2Tree {
    Map<Integer, Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart > pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int rootIn = inMap.get(root.val);
        int leftChildNum = rootIn - iStart;
        root.left = buildTree(preorder, pStart+1, pStart+leftChildNum, inorder, iStart, rootIn-1);
        root.right = buildTree(preorder, pStart+leftChildNum+1, pEnd, inorder, rootIn+1, iEnd);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        TreeNode root = new PreInOrder2Tree().buildTree(preorder, inorder);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
        System.out.println(root.left.left);
        System.out.println(root.left.right);
        System.out.println(root.right.left.val);
        System.out.println(root.right.right.val);
    }
}

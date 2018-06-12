package com.alex.tree;

import com.alex.common.TNode;

/**
 * Created by caozhennan on 2018/6/7.
 */
public class MaxBST {
    public static TNode max(TNode root) {
        return max(root, new int[3]);
    }

    // aux: for returning values, subtree size, min and max values in subtree
    private static TNode max(TNode node, int[] aux) {
        if (node == null) {
            return null;
        }
        aux[0] = 1;
        aux[1] = node.value;
        aux[2] = node.value;

        int[] auxl = new int[3];
        TNode left = max(node.left, auxl);
        if (left != null && node.left == left && node.value > auxl[2]) {
            aux[0] += auxl[0];
            aux[1] = auxl[1];
        }
        int[] auxr = new int[3];
        TNode right = max(node.right, auxr);
        if (right != null && node.right == right && node.value < auxr[1]) {
            aux[0] += auxr[0];
            aux[2] = auxr[2];
        }
        if (aux[0] >= auxl[0] && aux[0] >= auxr[0]) {
            return node;
        } else if (auxl[0] > auxr[0]) {
            aux[0] = auxl[0];
            aux[1] = auxl[1];
            aux[2] = auxl[2];
            return left;
        } else {
            aux[0] = auxr[0];
            aux[1] = auxr[1];
            aux[2] = auxr[2];
            return right;
        }
    }
}

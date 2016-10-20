package com.alex.tree;

/**
 * Created by Administrator on 2016/10/18.
 */
// Problem 12.2
public class RadixTree {

    private static class Node {
        String str;
        Node left;
        Node right;

        Node() {
        }
    }

    public RadixTree() {
        root = new Node();
    }

    private Node root;

    public boolean insert(String str) {
        Node n = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '0') {
                if (n.left == null) {
                    n.left = new Node();
                }
                n = n.left;
            } else if (c == '1') {
                if (n.right == null) {
                    n.right = new Node();
                }
                n = n.right;
            } else {
                throw new RuntimeException("invalid string");
            }
        }
        if (n.str != null) {
            return false;
        }
        n.str = str;
        return true;
    }

    public void preorder() {
        preorder(root);
    }
    private void preorder(Node node) {
        if (node == null) {
            return;
        }
        if (node.str != null) {
            System.out.print(node.str + " ");
        }
        preorder(node.left);
        preorder(node.right);
    }

    public static void main(String[] args) {
        RadixTree rt = new RadixTree();
        rt.insert("0");
        rt.insert("1011");
        rt.insert("10");
        rt.insert("100");
        rt.insert("011");
        rt.preorder();
    }
}

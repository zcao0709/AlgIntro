package com.alex.tree;

import com.alex.common.TreeNode;

import java.util.*;

/**
 * Created by Administrator on 2016/10/12.
 */
// Chapter 12
public class BinarySearchTree {
    private static class Node {
        private int key;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int key, Node left, Node right, Node parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(int val) {
            this(val, null, null, null);
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }

    private Node root;
    private static final int MAX_VALUE_LEN = 17;

    public BinarySearchTree(int[] data) {
        root = new Node(data[0]);
        makeTree(root, data, 0);
    }

    public BinarySearchTree() {
        root = null;
    }

    public void printTree() {
        inOrder4Print(root, 0, "H");
        System.out.println();
    }

    private void inOrder4Print(Node node, int height, String to) {
        if (node == null) {
            return;
        }
        inOrder4Print(node.right, height+1, "v");
        String val = to + node.key + to;
        int lenM = val.length();
        int lenL = (MAX_VALUE_LEN - lenM) / 2;
        int lenR = MAX_VALUE_LEN - lenM - lenL;
        val = getSpace(lenL).append(val).append(getSpace(lenR).toString()).toString();
        System.out.println(getSpace(height * MAX_VALUE_LEN).append(val));
        inOrder4Print(node.left, height+1, "^");
    }

    private StringBuilder getSpace(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(" ");
        }
        return sb;
    }

    private void makeTree(Node parent, int[] data, int index) {
        int left = index * 2 + 1;
        if (left < data.length && data[left] != 0) {
            Node node = new Node(data[left]);
            parent.left = node;
            node.parent = parent;
            makeTree(node, data, left);
        }
        int right = index * 2 + 2;
        if (right < data.length && data[right] != 0) {
            Node node = new Node(data[right]);
            parent.right = node;
            node.parent = parent;
            makeTree(node, data, right);
        }
    }

    public void inorderWithStack() {
        Deque<Node> s = new LinkedList<>();
        Node node = root;
        while (!s.isEmpty() || node != null) {
            if (node != null) {
                s.addLast(node);
                node = node.left;
            } else {
                node = s.removeLast();
                System.out.print(node.toString() + " ");
                node = node.right;
            }
        }
        System.out.println();
    }

    public void inorderWithParent() {
        if (root == null) {
            return;
        }
        Node prev = null;
        Node node = root;
        while (node != null) {
            if (prev == node.parent) {
                if (node.left == null) {
                    System.out.print(node.toString() + " ");
                }
                prev = node;
                node = node.left != null ? node.left : (node.right != null ? node.right : node.parent);
            } else if (prev == node.left) {
                System.out.print(node.toString() + " ");
                prev = node;
                node = node.right != null ? node.right : node.parent;
            } else {
                prev = node;
                node = node.parent;
            }
        }
    }

    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.toString() + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void preorderWithStack() {
        if (root == null) {
            return;
        }
        Deque<Node> s = new LinkedList<>();
        s.addLast(root);
        while (!s.isEmpty()) {
            Node node = s.removeLast();
            System.out.print(node.toString() + " ");
            if (node.right != null) {
                s.addLast(node.right);
            }
            if (node.left != null) {
                s.addLast(node.left);
            }
        }
        System.out.println();
    }

    public void preorderWithParent() {
        if (root == null) {
            return;
        }
        Node prev = null;
        Node node = root;
        while (node != null) {
            if (prev == node.parent) {
                System.out.print(node.toString() + " ");
                prev = node;
                node = node.left != null ? node.left : (node.right != null ? node.right : node.parent);
            } else if (prev == node.left) {
                prev = node;
                node = node.right != null ? node.right : node.parent;
            } else {
                prev = node;
                node = node.parent;
            }
        }
    }

    public void postorder() {
        postorder(root);
        System.out.println();
    }
    private void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.toString() + " ");
    }

    public void postorderWithStack() {
        if (root == null) {
            return;
        }
        Deque<Node> s = new LinkedList<>();
        Node node = root;
        s.addLast(node);
        Node top;
        while (!s.isEmpty()) {
            top = s.getLast();
            if (top.left != null && node != top.left && node != top.right) {
                s.addLast(top.left);
            } else if (top.right != null && node != top.right) {
                s.addLast(top.right);
            } else {
                node = s.removeLast();
                System.out.print(node.toString() + " ");
            }
        }
        System.out.println();
        TreeMap<String, String> m = new TreeMap<>();
        m.lowerKey("aa");
    }

    public void postorderWith2Stacks() {
        if (root == null) {
            return;
        }
        Deque<Node> s1 = new LinkedList<>();
        Deque<Node> s2 = new LinkedList<>();
        s1.addLast(root);
        while (!s1.isEmpty()) {
            Node n = s1.removeLast();
            s2.addLast(n);
            if (n.left != null) {
                s1.addLast(n.left);
            }
            if (n.right != null) {
                s1.addLast(n.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.removeLast().toString() + " ");
        }
        System.out.println();
    }

    public void postorderWithParent() {
        if (root == null) {
            return;
        }
        Node prev = null;
        Node node = root;
        while (node != null) {
            if (prev == node.parent) {
                if (node.left == null && node.right == null) {
                    System.out.print(node.toString() + " ");
                }
                prev = node;
                node = node.left != null ? node.left : (node.right != null ? node.right : node.parent);
            } else if (prev == node.left) {
                if (node.right == null) {
                    System.out.print(node.toString() + " ");
                }
                prev = node;
                node = node.right != null ? node.right : node.parent;
            } else {
                System.out.print(node.toString() + " ");
                prev = node;
                node = node.parent;
            }
        }
    }

    // values is in post-order
    public static BinarySearchTree array2BST(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        BinarySearchTree bst = new BinarySearchTree();
        bst.root = array2BST(values, 0, values.length-1);
        return bst;
    }

    private static Node array2BST(int[] values, int left, int right) {
        if (left == right) {
            return new Node(values[left]);
        }
        int midVal = values[right];
        Node n = new Node(midVal);
        int i = left;
        while (values[i] < midVal) {
            i++;
        }
        n.left = i <= left ? null : array2BST(values, left, i-1);
        n.right = i >= right ? null : array2BST(values, i, right-1);
        if (n.left != null) {
            n.left.parent = n;
        }
        if (n.right != null) {
            n.right.parent = n;
        }
        return n;
    }

    private int keyOrException(Node node) {
        if (node != null) {
            return node.key;
        }
        throw new RuntimeException("No such value");
    }

    public int floor(int key) {
        return keyOrException(floorNode(key));
    }
    private Node floorNode(int key) {
        Node n = root;
        while (n != null) {
            if (key > n.key) {
                if (n.right != null) {
                    n = n.right;
                } else {
                    return n;
                }
            } else if (key < n.key) {
                if (n.left != null) {
                    n = n.left;
                } else {
                    Node p = n.parent;
                    while (p != null && n == p.left) {
                        n = p;
                        p = p.parent;
                    }
                    return p;
                }
            } else {
                return n;
            }
        }
        return null;
    }
    private Node floorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return highestNode(node.left);
        } else {
            Node p = node.parent;
            while (p != null && node == p.left) {
                node = p;
                p = p.parent;
            }
            return p;
        }
    }

    public int ceiling(int key) {
        return keyOrException(ceilingNode(key));
    }
    private Node ceilingNode(int key) {
        Node n = root;
        while (n != null) {
            if (key < n.key) {
                if (n.left != null) {
                    n = n.left;
                } else {
                    return n;
                }
            } else if (key > n.key) {
                if (n.right != null) {
                    n = n.right;
                } else {
                    Node p = n.parent;
                    while (p != null && p.right == n) {
                        n = p;
                        p = p.parent;
                    }
                    return p;
                }
            } else {
                return n;
            }
        }
        return null;
    }
    private Node ceilingNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return lowestNode(node.right);
        } else {
            Node p = node.parent;
            while (p != null && node == p.right) {
                node = p;
                p = p.parent;
            }
            return p;
        }
    }

    public boolean search(int key) {
        if (searchNode(key) == null) {
            return false;
        } else {
            return true;
        }
    }
    private Node searchNode(int key) {
        Node n = root;
        while (n != null) {
            if (key > n.key) {
                n = n.right;
            } else if (key < n.key) {
                n = n.left;
            } else {
                return n;
            }
        }
        return null;
    }

    public int lowest() {
        return keyOrException(lowestNode(root));
    }
    private Node lowestNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int highest() {
        return keyOrException(highestNode(root));
    }
    private Node highestNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public boolean insert(int key) {
        Node n = new Node(key);
        return insertNode(n);
    }
    private boolean insertNode(Node node) {
        Node p = null;
        Node n = root;
        while (n != null) {
            p = n;
            if (node.key > n.key) {
                n = n.right;
            } else if (node.key < n.key) {
                n = n.left;
            } else {
                return false;
            }
        }
        node.parent = p;
        if (p == null) {
            root = node;
        } else {
            if (node.key < p.key) {
                p.left = node;
            } else {
                p.right = node;
            }
        }
        return true;
    }

    public boolean remove(int key) {
        Node n = searchNode(key);
        if (n == null) {
            return false;
        } else {
            removeNode(n);
            return true;
        }
    }
    private void removeNode(Node node) {
        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node c = lowestNode(node.right);
            if (c.parent != node) {
                transplant(c, c.right);
                c.right = node.right;
                c.right.parent = c;
            }
            transplant(node, c);
            c.left = node.left;
            c.left.parent = c;
        }
        node.left = null;
        node.right = null;
    }

    private void transplant(Node oldChild, Node newChild) {
        if (oldChild == null) {
            throw new RuntimeException("internal error: no such node");
        }
        Node p = oldChild.parent;
        if (p == null) {
            root = newChild;
        } else {
            if (oldChild == p.left) {
                p.left = newChild;
            } else {
                p.right = newChild;
            }
            oldChild.parent = null;
        }
        if (newChild != null) {
            newChild.parent = p;
        }
    }

    public static void main(String[] args) {
		int[] values = new int[]{2, 1, 3, 10, 8, 6, 12, 4};
		BinarySearchTree bst = array2BST(values);
        bst.printTree();
//        bst.postorder();
//        bst.postorderWithStack();
//        bst.postorderWith2Stacks();
        System.out.println(bst.ceiling(11));
    }
}

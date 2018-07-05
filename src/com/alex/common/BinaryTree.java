package com.alex.common;

import java.util.*;
import java.lang.Math;

/**
 * Created by caozhennan on 2018/7/3.
 */
public class BinaryTree {
    public TNode root;

    private static final int MAX_VALUE_LEN = 17;

    public BinaryTree() {
    }

    public void add(int val) {
        if (root == null) {
            root = new TNode(val);

        } else {
            Queue<TNode> q = new LinkedList<>();
            q.offer(root);

            while (true) {
                TNode n = q.poll();
                if (n.left == null) {
                    n.left = new TNode(val);
                    break;
                } else {
                    q.offer(n.left);
                }
                if (n.right == null) {
                    n.right = new TNode(val);
                    break;
                } else {
                    q.offer(n.right);
                }
            }
        }
    }

    private static int distinctRandom(Set<Integer> set, Random r, int limit) {
        int i = r.nextInt(limit);
        while (set.contains(i)) {
            i = r.nextInt(limit);
        }
        set.add(i);
        return i;
    }

    public static BinaryTree valueOf(int num) {
        BinaryTree tree = new BinaryTree();

        Set<Integer> dist = new HashSet<>();
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            tree.add(distinctRandom(dist, r, 100));
        }
        return tree;
    }

    public static BinaryTree valueOf(int[] nums) {
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < nums.length; i++) {
            tree.add(nums[i]);
        }
        return tree;
    }

    public static BinaryTree bstOf(int[] nums) {
        return null;
    }

    public void printTree() {
        inOrder4Print(root, 0, "H");
        System.out.println();
    }

    private void inOrder4Print(TNode node, int height, String to) {
        if (node == null) {
            return;
        }
        inOrder4Print(node.right, height+1, "v");
        String val = to + node.value + to;
        int lenM = val.length();
        int lenL = (MAX_VALUE_LEN - lenM) / 2;
        int lenR = MAX_VALUE_LEN - lenM - lenL;
        val = getSpace(lenL).append(val).append(getSpace(lenR)).toString();
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

    public TNode maxSubBST() {
        int[] aux = new int[3];
        TNode n = maxSubBST(root, aux);
        System.out.println(Arrays.toString(aux));
        return n;
    }

    // aux: for returning, subtree size, min and max values in subtree
    private TNode maxSubBST(TNode node, int[] aux) {
        if (node == null) {
            return null;
        }
        aux[0] = 1;
        aux[1] = node.value;
        aux[2] = node.value;

        int[] auxl = new int[3];
        TNode left = maxSubBST(node.left, auxl);
        int[] auxr = new int[3];
        TNode right = maxSubBST(node.right, auxr);

        TNode ret;
        if (left == null && right == null) {
            ret = node;

        } else if (left != null && node.left == left && node.value > auxl[2] &&
                right != null && node.right == right && node.value < auxr[1]) {
            aux[0] = auxl[0] + auxr[0] + 1;
            aux[1] = auxl[1];
            aux[2] = auxr[2];
            ret = node;

        } else if (right == null || auxl[0] > auxr[0]) {
            aux[0] = auxl[0];
            aux[1] = auxl[1];
            aux[2] = auxl[2];
            ret = left;

        } else {
            aux[0] = auxr[0];
            aux[1] = auxr[1];
            aux[2] = auxr[2];
            ret = right;
        }
        System.out.println(node + ", " + Arrays.toString(aux));
        return ret;
    }

    private class Record {
        public int l;
        public int r;

        public Record() {
        }
    }
    public int maxBST() {
        return maxBST(root, new HashMap<>());
    }

    private int maxBST(TNode node, Map<TNode, Record> map) {
        if (node == null) {
            return 0;
        }
        int lm = maxBST(node.left, map);
        int rm = maxBST(node.right, map);
        modifyMap(node.left, node, map, true);
        modifyMap(node.right, node, map, false);
        Record lr = map.get(node.left);
        Record rr = map.get(node.right);
        Record r = new Record();
        r.l = lr == null ? 0 : lr.l + lr.r + 1;
        r.r = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(node, r);
        return Math.max(r.l + r.r + 1, Math.max(lm, rm));
    }

    private int modifyMap(TNode child, TNode parent, Map<TNode, Record> map, boolean left) {
        if (parent == null || child == null || !map.containsKey(child)) {
            return 0;
        }
        Record r = map.get(child);
        if ((left && child.value > parent.value) || (!left && child.value < parent.value)) {
            map.remove(child);
            return r.l + r.r + 1;
        }
        int diff = modifyMap(left ? child.right : child.left, parent, map, left);
        if (left) {
            r.r -= diff;
        } else {
            r.l -= diff;
        }
        map.put(child, r);
        return diff;
    }

    public boolean isBST() {
        prev= null;
        return isBST(root);
    }
    private TNode prev;
    private boolean isBST(TNode node) {
        System.out.println("prev=" + prev);
        System.out.println("node=" + node);
        System.out.println();
        if (node == null) {
            return true;
        }
        if (!isBST(node.left)) {
            return false;
        }
        if (prev != null && prev.value > node.value) {
            return false;
        }
        prev = node;
        return isBST(node.right);
    }

    public static void main(String[] args) {
//        BinaryTree tree = BinaryTree.valueOf(20);
        BinaryTree tree = BinaryTree.valueOf(new int[]{7, 4, 11});//, 2, 6, 8, 14, 5, 9, 1, 10, 12, 3, 13, 15});
        tree.printTree();

//        System.out.println(tree.maxSubBST());
//        System.out.println();
//        System.out.println(tree.maxBST());
        System.out.println(tree.isBST());
    }
}

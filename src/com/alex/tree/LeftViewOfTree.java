package com.alex.tree;

import com.alex.common.TNode;

import java.util.*;

/**
 * Created by caozhennan on 2019/10/11 5:04 下午.
 */
public class LeftViewOfTree {
    private static int viewed;

    public static List<TNode> leftView1(TNode root) {
        List<TNode> ret = new LinkedList<>();
        viewed = -1;
        leftView(root, ret, 0);
        return ret;
    }

    public static List<TNode> leftView2(TNode root) {
        List<TNode> ret = new LinkedList<>();
        Set<Integer> viewed2 = new HashSet<>();
        leftView(root, ret, viewed2, 0);
        return ret;
    }

    public static List<TNode> leftView3(TNode root) {
        List<TNode> ret = new LinkedList<>();
        int[] viewed = new int[1];
        viewed[0] = -1;
        leftView(root, ret, viewed, 0);
        return ret;
    }

    public static List<TNode> leftView4(TNode root) {
        List<TNode> ret = new LinkedList<>();
        if (root != null) {
            Queue<TNode> layer = new LinkedList<>();
            layer.offer(root);

            while (!layer.isEmpty()) {
                TNode n = layer.poll();
                ret.add(n);
                Queue<TNode> nextLayer = new LinkedList<>();
                while (n != null) {
                    if (n.left != null) {
                        nextLayer.offer(n.left);
                    }
                    if (n.right != null) {
                        nextLayer.offer(n.right);
                    }
                    n = layer.poll();
                }
                layer = nextLayer;
            }
        }
        return ret;
    }

    private static void leftView(TNode node, List<TNode> list, int layer) {
        if (node != null) {
            if (viewed < layer) {
                list.add(node);
                viewed = layer;
            }
            leftView(node.left, list, layer+1);
            leftView(node.right, list, layer+1);
        }
    }

    private static void leftView(TNode node, List<TNode> list, Set<Integer> viewed, int layer) {
        if (node != null) {
            if (!viewed.contains(layer)) {
                list.add(node);
                viewed.add(layer);
            }
            leftView(node.left, list, viewed, layer+1);
            leftView(node.right, list, viewed, layer+1);
        }
    }

    private static void leftView(TNode node, List<TNode> list, int[] viewed, int layer) {
        if (node != null) {
            if (viewed[0] < layer) {
                list.add(node);
                viewed[0] = layer;
            }
            leftView(node.left, list, viewed, layer+1);
            leftView(node.right, list, viewed, layer+1);
        }
    }

    public static void main(String[] args) {
        TNode root = new TNode(0);

        root.left = new TNode(1);
        root.right = new TNode(2);

        root.left.right = new TNode(5);
        root.right.right = new TNode(3);

        root.right.right.right = new TNode(4);

        List<TNode> ret = leftView1(root);
        System.out.println(ret.toString());

        ret = leftView2(root);
        System.out.println(ret.toString());

        ret = leftView3(root);
        System.out.println(ret.toString());

        ret = leftView4(root);
        System.out.println(ret.toString());
    }
}

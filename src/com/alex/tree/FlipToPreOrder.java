package com.alex.tree;

import com.alex.common.TNode;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/15 3:11 下午.
 *
 * 给定一个有 N 个节点的二叉树，每个节点都有一个不同于其他节点且处于 {1, ..., N} 中的值。
 * 通过交换节点的左子节点和右子节点，可以翻转该二叉树中的节点。
 * 考虑从根节点开始的先序遍历报告的 N 值序列。将这一 N 值序列称为树的行程。
 * （回想一下，节点的先序遍历意味着我们报告当前节点的值，然后先序遍历左子节点，再先序遍历右子节点。）
 * 我们的目标是翻转最少的树中节点，以便树的行程与给定的行程 voyage 相匹配。
 * 如果可以，则返回翻转的所有节点的值的列表。你可以按任何顺序返回答案。
 * 如果不能，则返回列表 [-1]。
 */
public class FlipToPreOrder {
    HashMap<Integer, Integer> childNum = new HashMap<>();

    public List<Integer> flipMatchVoyage(TNode root, int[] voyage) {
        List<Integer> ret = new LinkedList<>();
        if (root == null) {
            if (voyage != null && voyage.length != 0) {
                ret.add(-1);
                return ret;
            }
            return ret;
        }
        if (root.value != voyage[0]) {
            ret.add(-1);
            return ret;
        }
        if (voyage.length != childNumByPostOrder(root) + 1) {
            ret.add(-1);
            return ret;
        }
        System.out.println(childNum);
        List<TNode> stack = new LinkedList<>();
        stack.add(root);
        int pos = 0;
        while (!stack.isEmpty()) {
            System.out.println(Arrays.toString(stack.toArray(new TNode[0])) + pos);
            TNode node = stack.remove(0);
            if (node.left != null && node.right != null) {
                if (node.left.value != voyage[leftChildPos(pos)] || node.right.value != voyage[rightChildPos(pos, node.left)]) {
                    TNode tmp = node.left;
                    node.left = node.right;
                    node.right = tmp;

                    if (node.left.value != voyage[leftChildPos(pos)] || node.right.value != voyage[rightChildPos(pos, node.left)]) {
                        ret.clear();
                        ret.add(-1);
                        return ret;
                    } else {
                        ret.add(node.value);
                    }
                }
                stack.add(0, node.right);
                stack.add(0, node.left);

            } else if (node.left != null) {
                if (node.left.value != voyage[leftChildPos(pos)]) {
                    ret.clear();
                    ret.add(-1);
                    return ret;
                }
                stack.add(0, node.left);

            } else if (node.right != null) {
                if (node.right.value != voyage[rightChildPos(pos, null)]) {
                    ret.clear();
                    ret.add(-1);
                    return ret;
                }
                stack.add(0, node.right);
            }
            pos++;
        }
        return ret;
    }

    private int leftChildPos(int pos) {
        return pos + 1;
    }

    private int rightChildPos(int pos, TNode leftChild) {
        if (leftChild == null) {
            return pos + 1;
        } else {
            return pos + 1 + 1 + childNum.get(leftChild.value);
        }
    }

    private int childNumByPostOrder(TNode node) {
        if (node.left == null && node.right == null) {
            childNum.put(node.value, 0);
            return 0;
        } else {
            int n = 0;
            if (node.left != null) {
                n++;
                n += childNumByPostOrder(node.left);
            }
            if (node.right != null) {
                n++;
                n += childNumByPostOrder(node.right);
            }
            childNum.put(node.value, n);
            return n;
        }
    }

    public static void main(String[] args) {
        TNode root = new TNode(2);
        root.left = new TNode(1);
        root.right = new TNode(4);
        root.left.left = new TNode(5);
//        root.left.right = new TNode(2);
//        root.left.right.left = new TNode(7);
//        root.left.right.right = new TNode(4);
        root.right.left = new TNode(3);
//        root.right.right= new TNode(3);
        int[] voyage = new int[]{2, 4, 3, 1, 5};

        System.out.println(Arrays.toString(new FlipToPreOrder().flipMatchVoyage(root, voyage).toArray(new Integer[0])));
    }
}

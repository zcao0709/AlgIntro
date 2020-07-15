package com.alex.tree;

import com.alex.common.TNode;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/13 6:06 下午.
 *
 * 给你 root1 和 root2 这两棵二叉搜索树。
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序
 *
 * 每棵树最多有 5000 个节点。
 * 每个节点的值在 [-10^5, 10^5] 之间。
 */
public class TwoBST2SortedList {
    public List<Integer> getAllElements(TNode root1, TNode root2) {
        List<Integer> list1 = new LinkedList<>();
        inOrder(root1, list1);

        List<Integer> list2 = new LinkedList<>();
        inOrder(root2, list2);

        int size = list1.size() + list2.size();
        List<Integer> ret = new ArrayList<>(size);
        Integer[] array1 = list1.toArray(new Integer[0]);
        Integer[] array2 = list2.toArray(new Integer[0]);
        for (int i = 0, j = 0, k = 0; i < size; i++) {
            if (k == array2.length || j < array1.length && array1[j].compareTo(array2[k]) <= 0) {
                ret.add(array1[j++]);
            } else {
                ret.add(array2[k++]);
            }
        }
        return ret;
    }

    private void inOrder(TNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.value);
        inOrder(node.right, list);
    }

    public List<Integer> getAllElements2(TNode root1, TNode root2) {
        BSTIterator iter1 = new BSTIterator(root1);
        BSTIterator iter2 = new BSTIterator(root2);

        List<Integer> ret = new LinkedList<>();
        Integer last1 = null;
        Integer last2 = null;
        while (true) {
            if (!iter1.hasNext() && !iter2.hasNext()) {
                if (last1 != null) {
                    ret.add(last1);
                }
                if (last2 != null) {
                    ret.add(last2);
                }
                break;
            } else if (!iter1.hasNext() && last1 == null) {
                if (last2 != null) {
                    ret.add(last2);
                    last2 = null;
                }
                ret.add(iter2.next());
            } else if (!iter2.hasNext() && last2 == null) {
                if (last1 != null) {
                    ret.add(last1);
                    last1 = null;
                }
                ret.add(iter1.next());
            } else {
                Integer curr1 = last1 == null ? iter1.next() : last1;
                Integer curr2 = last2 == null ? iter2.next() : last2;
                if (curr1.compareTo(curr2) <= 0) {
                    ret.add(curr1);
                    last1 = null;
                    last2 = curr2;
                } else {
                    ret.add(curr2);
                    last2 = null;
                    last1 = curr1;
                }
            }
        }
        return ret;
    }

    private void inOrderAdd(TNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrderAdd(node.left, list);
        list.add(node.value);
        inOrderAdd(node.right, list);
    }

    private void inOrderInsert(TNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrderInsert(node.left, list);
        int idx = Collections.binarySearch(list, node.value);
        if (idx < 0) {
            idx = -(idx+1);
        }
        list.add(idx, node.value);
        inOrderInsert(node.right, list);
    }
}

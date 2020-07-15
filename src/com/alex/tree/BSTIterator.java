package com.alex.tree;

import com.alex.common.TNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2020/7/13 3:44 下午.
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */
public class BSTIterator {
    List<TNode> iter = new LinkedList<>();

    public BSTIterator(TNode root) {
        while (root != null) {
            iter.add(0, root);
            root = root.left;
        }
    }

    public int next() {
        if (hasNext()) {
            TNode ret = iter.remove(0);
            TNode node = ret.right;
            while (node != null) {
                iter.add(0, node);
                node = node.left;
            }
            return ret.value;
        }
        throw new IllegalStateException();
    }

    public boolean hasNext() {
        return !iter.isEmpty();
    }
}

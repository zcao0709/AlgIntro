package com.alex.tree;

import com.alex.common.TNode;
import org.apache.commons.io.output.TaggedOutputStream;
import org.apache.spark.sql.sources.In;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/14 7:01 下午.
 */
public class DistanceK {
    public List<Integer> distanceK(TNode root, TNode target, int k) {
        List<Integer> ret = new LinkedList<>();
        Map<TNode, TNode> parent = new HashMap<>();
        List<TNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TNode node = queue.remove(0);
            if (node == target) {
                break;
            }
            if (node.left != null) {
                queue.add(node.left);
                parent.put(node.left, node);
            }
            if (node.right != null) {
                queue.add(node.right);
                parent.put(node.right, node);
            }
        }
        TNode lastTarget = null;
        while (target != null && k >= 0) {
            findChildren(target, lastTarget, ret, k);
            lastTarget = target;
            target = parent.get(lastTarget);
            k--;
        }
        return ret;
    }

    private void findChildren(TNode node, TNode child, List<Integer> ret, int k) {
        if (node == null || node == child) {
            return;
        }
        if (k == 0) {
            ret.add(node.value);
        }
        findChildren(node.left, child, ret, k-1);
        findChildren(node.right, child, ret, k-1);
    }

    public static void main(String[] args) {
        TNode root = new TNode(3);
        root.left = new TNode(5);
        root.right = new TNode(1);
        root.left.left = new TNode(6);
        root.left.right = new TNode(2);
        root.left.right.left = new TNode(7);
        root.left.right.right = new TNode(4);
        root.right.left = new TNode(0);
        root.right.right= new TNode(8);

        DistanceK dk = new DistanceK();
        System.out.println(Arrays.toString(dk.distanceK(root, root.left, 2).toArray(new Integer[0])));
    }
}

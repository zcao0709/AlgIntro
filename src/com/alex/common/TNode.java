package com.alex.common;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by caozhennan on 2017/11/21.
 */
public class TNode {
    public int value;
    public TNode left;
    public TNode right;

    public TNode(int value) {
        this.value = value;
    }

    public static TNode create(int num) {
        Random r = new Random();
        Queue<TNode> q = new LinkedList<>();
        TNode h = new TNode(r.nextInt(100));
        q.offer(h);
        int i = 1;

        while (!q.isEmpty()) {
            TNode n = q.poll();
            n.left = new TNode(r.nextInt(100));
            q.offer(n.left);
            if (++i == num) {
                break;
            }
            n.right = new TNode(r.nextInt(100));
            q.offer(n.right);
            if (++i == num) {
                break;
            }
        }
        return h;
    }
}

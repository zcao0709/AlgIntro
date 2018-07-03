package com.alex.common;

import com.alex.misc.PartitionList;

import java.util.Random;

/**
 * Created by caozhennan on 2017/11/21.
 */
public class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return value + " -> ";
    }

    public static Node create(int num) {
        Node head = null;

        Random r = new Random();
        for (int i = 0; i < num; i++) {
            Node n = new Node(r.nextInt(100));
            n.next = head;
            head = n;
        }
        return head;
    }
}

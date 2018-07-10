package com.alex.common;

/**
 * Created by caozhennan on 2018/7/9.
 */
public class DoubleList {
    public DNode head;

    public DoubleList() {
    }

    public void add(int value) {
        DNode n = new DNode(value);
        n.next = head;
        if (head != null) {
            head.next.prev = n;
        }
        head = n;
    }

    public DNode getFirst(int value) {
        DNode n = head;
        while (n != null && n.value != value) {
            n = n.next;
        }
        return n;
    }

    private void remove(DNode node) {
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
        }
    }

    public void remove(int value) {
        remove(getFirst(value));
    }
}

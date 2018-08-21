package com.alex.common;

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

    public static Node createSingleNum(int value) {
        Node n = new Node(0);
        n.linkValue(value);
        return n;
    }

    // keep value < 10
    public int addValue(int value) {
        this.value += value;
        if (this.value >= 10) {
            this.value -= 10;
            return 1;
        } else {
            return 0;
        }
    }

    private void linkValue(int value) {
        int carry = addValue(value);
        if (carry > 0) {
            next = new Node(carry);
        }
    }

    public void link(int value) {
        if (next == null) {
            next = createSingleNum(value);
        } else {
            next.linkValue(value);
        }
    }

    @Override
    public String toString() {
        return value + " -> ";
    }
}

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

    @Override
    public String toString() {
        return value + " -> ";
    }
}

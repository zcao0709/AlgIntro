package com.alex.common;

/**
 * Created by caozhennan on 2018/7/9.
 */
public class DNode {
    int value;
    DNode prev;
    DNode next;

    public DNode(int value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return value + " <-> ";
    }
}

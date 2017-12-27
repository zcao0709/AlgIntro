package com.alex.common;

/**
 * Created by caozhennan on 2017/12/27.
 */
public class SingleList {
    public Node head;
    public Node tail;
    public int size;

    public SingleList() {
    }

    public void add(int value) {
        Node n = new Node(value);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    public void reverse() {
        if (head == null) {
            return;
        }
        Node prev = null;
        Node curr = head;
        head = tail;
        tail = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }

    public int asInt() {
        int value = 0;
        Node n = head;
        int i = 1;
        while (n != null) {
            value += n.value * i;
            i *= 10;
            n = n.next;
        }
        return value;
    }

    public void addValue(Node n, int value) {
        if (n == null) {
            add(value);
            return;
        }
        n.value += value;
        if (n.value > 9) {
            n.value -= 10;
            addValue(n.next, 1);
        }
    }
}

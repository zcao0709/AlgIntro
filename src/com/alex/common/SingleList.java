package com.alex.common;

import java.util.Random;

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
        size++;
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

    public Node kthBack(int k) {
        Node fast = head;
        for (; k > 0 && fast != null; k--) {
            fast = fast.next;
        }
        if (k > 0) {
            throw new IllegalArgumentException("list is too short");
        }
        Node slow = head;
        for (; fast != null; fast = fast.next) {
            slow = slow.next;
        }
        return slow;
    }

    public Node midNode() {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public boolean hasCircle() {
        if (head == null || head.next == null) {
            return false;
        }
        Node fast = head;
        Node slow = head;
        int i = 0;

        while (fast != null && fast.next != null) {
            i++;
            System.out.println("fast = " + fast);
            System.out.println("slow = " + slow);
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
//                System.out.println("i = " + i);
                return true;
            }
        }
        return false;
    }

    public Node circleStart() {
        if (head == null || head.next == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        boolean meet = false;

        while (fast != null && fast.next != null) {
            if (meet) {
                fast = fast.next;
            } else {
                fast = fast.next.next;
            }
            slow = slow.next;
//            System.out.println("fast = " + fast);
//            System.out.println("slow = " + slow);
            if (fast == slow) {
                if (meet) {
                    return fast;
                }
                meet = true;
                fast = head;
            }
        }
        return null;
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

    @Override
    public String toString() {
        Node stop = circleStart();
        StringBuilder sb = new StringBuilder("(").append(size).append("): ");
        Node n = head;
        int i = 1;
        boolean meet = false;
        while (n != null) {
            sb.append(i).append("/").append(n.toString());
            if (n == stop) {
                if (!meet)
                    meet = true;
                else
                    break;
            }
            n = n.next;
            i++;

        }
        if (stop == null) {
            sb.append("null");
        } else {
            sb.append("circle");
        }
        return sb.toString();
    }

    public static SingleList create(int num) {
        SingleList list = new SingleList();

        Random r = new Random();
        for (int i = 0; i < num; i++) {
            list.add(r.nextInt(100));
        }
        return list;
    }

    public static void main(String[] args) {
        SingleList list = SingleList.create(14);
        System.out.println(list.toString());
//        System.out.println(list.midNode());

        Node kth = list.kthBack(8);
        System.out.println(kth);
        list.tail.next = kth;

//        System.out.println(list.hasCircle());
        System.out.println(list.circleStart());
    }
}

package com.alex.list;

import com.alex.common.Node;
import com.alex.common.SingleList;

/**
 * Created by caozhennan on 2018/7/21.
 */
public class Add {
    public static Node add(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return n1 == null ? n2 : n1;
        }
        Node nr1 = reverse(n1);
        Node nr2 = reverse(n2);

        int carry = 0;
        Node prev = null;
        for (n1 = nr1, n2 = nr2; n1 != null && n2 != null; n1 = n1.next, n2 = n2.next) {
            carry = n1.addValue(n2.value + carry);
            prev = n1;
        }
        if (n1 == null) {
            prev.next = n2;
            n1 = n2;
        }
        while (carry > 0) {
            if (n1 == null) {
                prev.next = new Node(carry);
                break;
            } else {
                carry = n1.addValue(carry);
            }
            prev = n1;
            n1 = n1.next;
        }
        return reverse(nr1);
    }

    public static Node add2(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return n1 == null ? n2 : n1;
        }
        Node nr1 = reverse(n1);
        Node nr2 = reverse(n2);

        Node prev = null;
        Node head = null;
        for (n1 = nr1, n2 = nr2; n1 != null && n2 != null; n1 = n1.next, n2 = n2.next) {
            int value = n1.value + n2.value;
            if (head == null) {
                head = Node.createSingleNum(value);
                prev = head;
            } else {
                prev.link(value);
                prev = prev.next;
            }
        }
        Node n = n1 == null ? n2 : n1;
        while (n != null) {
            prev.link(n.value);
            prev = prev.next;
            n = n.next;
        }
        return reverse(head);
    }

    public static void main(String[] args) {
        SingleList sl1 = SingleList.create(2, 10);
        SingleList sl2 = SingleList.create(3, 10);

        System.out.println(sl1.toString());
        System.out.println(sl2.toString());

        sl1.head = add2(sl1.head, sl2.head);
        System.out.println(sl1.toString());
    }

    private static Node reverse(Node node) {
        Node prev = null;

        while (node != null) {
            Node next = node.next;
            node.next = prev;

            prev = node;
            node = next;
        }
        return prev;
    }
}

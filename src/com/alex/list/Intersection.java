package com.alex.list;

import com.alex.common.Node;
import com.alex.common.SingleList;

/**
 * Created by caozhennan on 2018/7/2.
 */
public class Intersection {
    public static Node inter(SingleList list1, SingleList list2) {
        Node circle1 = list1.circleStart();
        Node circle2 = list2.circleStart();
//        System.out.println(circle1);
//        System.out.println(circle2);

        // no circle
        if (circle1 == null && circle1 == null) {
            return interNoCircle(list1.head, list2.head, null);

        // both have circle
        } else if (circle1 != null && circle2 != null) {
            // intersection is out of circle
            if (circle1 == circle2) {
                return interNoCircle(list1.head, list2.head, circle1);
            // intersection is on circle
            } else {
                Node n = circle1.next;
                while (n != circle1 && n != circle2) {
                    n = n.next;
                }
                if (n == circle2) {
                    return circle2;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    private static Node interNoCircle(Node head1, Node head2, Node end) {
        int diff = 0;
        Node n = head1.next;
        while (n != end) {
            diff++;
            n = n.next;
        }
        n = head2.next;
        while (n != end) {
            diff--;
            n = n.next;
        }
        Node longer, shorter;
        if (diff >= 0) {
            longer = head1;
        } else {
            diff = Math.abs(diff);
            longer = head2;
        }
        shorter = longer == head1 ? head2 : head1;
        for (; diff > 0; diff--) {
            longer = longer.next;
        }
        while (longer != shorter && longer != end && shorter != end) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer;
    }

    public static void main(String[] args) {
        SingleList list1 = SingleList.create(25);
        Node mid = list1.midNode();

        SingleList list2 = SingleList.create(10);
        list2.tail.next = mid;
        list2.size += (list1.size / 2 + 1);

        // make a circle
        Node kth = list1.kthBack(8);
        list1.tail.next = kth;

        System.out.println(list1);
        System.out.println(list2);
        System.out.println("mid: " + mid);
        System.out.println("kth: " + kth);

        System.out.println(inter(list1, list2));
    }
}

package com.alex.misc;

import com.alex.common.Node;
import com.alex.common.SingleList;

import java.util.*;

/**
 * Created by caozhennan on 2017/12/26.
 */
public class SingleListAddition {

    public static int add(SingleList list1, SingleList list2) {
        SingleList op1, op2;
        if (list1.size >= list2.size) {
            op1 = list1;
            op2 = list2;
        } else {
            op1 = list2;
            op2 = list1;
        }
        op1.reverse();
        op2.reverse();

        Node n1 = op1.head;
        Node n2 = op2.head;
        while (n1 != null) {
            if (n2 == null) {
                break;
            }
            op1.addValue(n1, n2.value);
            n1 = n1.next;
            n2 = n2.next;
        }
        return op1.asInt();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        SingleList op1 = new SingleList();
        for (int i = 0; i < x; i++) {
            op1.add(sc.nextInt());
        }

        x = sc.nextInt();
        SingleList op2 = new SingleList();
        for (int i = 0; i < x; i++) {
            op2.add(sc.nextInt());
        }
        System.out.println(add(op1, op2));
    }
}

package com.alex.misc;

import com.alex.common.Node;
import com.alex.common.SingleList;

import java.util.Scanner;

/**
 * Created by caozhennan on 2017/11/20.
 */
public class PartitionList {

    public static Node partition(Node node, int pivot) {
        if (node.next == null) {
            return node;
        }
        Node bh = null;
        Node bt = null;
        Node eh = null;
        Node et = null;
        Node sh = null;
        Node st = null;

        Node n = node;
        while (n != null) {
            Node next = n.next;
            n.next = null;

            if (n.value > pivot) {
                if (bh == null) {
                    bh = n;
                    bt = n;
                } else {
                    bt.next = n;
                    bt = n;
                }
            } else if (n.value == pivot) {
                if (eh == null) {
                    eh = n;
                    et = n;
                } else {
                    et.next = n;
                    et = n;
                }
            } else {
                if (sh == null) {
                    sh = n;
                    st = n;
                } else {
                    st.next = n;
                    st = n;
                }
            }
            n = next;
        }
        if (et == null) {
            eh = bh;
        } else {
            et.next = bh;
        }
        if (st == null) {
            sh = eh;
        } else {
            st.next = eh;
        }
        return sh;
    }

    public static void main(String[] args) {
        SingleList list = SingleList.create(10);
        System.out.println(list);

        Scanner sc = new Scanner(System.in);
        int pivot = sc.nextInt();

        System.out.println(partition(list.head, pivot));
    }

}

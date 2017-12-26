package com.alex.misc;

import java.util.*;

/**
 * Created by caozhennan on 2017/12/26.
 */
public class LinkedListAddition {

    public static List<Integer> add(List<Integer> op1, List<Integer> op2) {
        if (op2 == null || op2.size() == 0) {
            return op1;
        }
        int over = 0;

        int m = op2.size();
        for (int i = op1.size() - 1; i >= 0; i--) {
            m--;
            if (m < 0 && over == 0) {
                return op1;
            }
            int op2V = m >= 0 ? op2.get(m) : 0;
            int sum = op1.get(i) + op2V + over;
            if (sum > 9) {
                over = 1;
                sum -= 10;
            } else {
                over = 0;
            }
            op1.set(i, sum);
        }
        if (over > 0) {
            op1.add(0, over);
        }
        return op1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        List<Integer> op1 = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            op1.add(sc.nextInt());
        }
        x = sc.nextInt();
        List<Integer> op2 = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            op2.add(sc.nextInt());
        }
        List<Integer> ret;
        if (op1.size() >= op2.size()) {
            ret = add(op1, op2);
        } else {
            ret = add(op2, op1);
        }
        for (int i = 0; i < ret.size(); i++) {
            System.out.print(ret.get(i) + " ");
        }
        System.out.println();
    }
}

package com.alex.tree;

import java.util.Scanner;

/**
 * Created by caozhennan on 2018/2/8.
 */
public class TreePreMid2Post {

    public static String convert(String pre, String mid) {
        StringBuilder sb = new StringBuilder(pre.length());
        convert(pre, mid, sb);
        return sb.toString();
    }

    private static void convert(String pre, String mid, StringBuilder sb) {
        if (pre.length() == 1) {
            sb.insert(0, pre);
            return;
        }
        String sp = pre.substring(0, 1);
        sb.insert(0, sp);

        int leftLen = mid.indexOf(sp);
        int rightLen = mid.length() - leftLen - 1;
        if (rightLen > 0)
            convert(pre.substring(leftLen + 1), mid.substring(leftLen + 1), sb);
        if (leftLen > 0)
            convert(pre.substring(1, 1 + leftLen), mid.substring(0, leftLen), sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pre = sc.nextLine();
        String mid = sc.nextLine();

        System.out.println(convert(pre, mid));
    }
}

package com.alex.misc;

import java.util.*;

/**
 * Created by caozhennan on 2018/6/3.
 */
public class Match {

    public static boolean match(String matcher, String pattern) {
        if (matcher.length() == 0 || pattern.length() == 0) {
            throw new IllegalArgumentException("argument size is 0");
        }
        String[] ms = matcher.split(" ");

        if (ms.length != pattern.length()) {
            return false;
        } else if (ms.length == 1 && pattern.length() == 1) {
            return true;
        }
        String[] s1 = norm(ms);

        String[] ps = new String[pattern.length()];
        for (int i = 0; i < ps.length; i++) {
            ps[i] = pattern.substring(i, i+1);
        }
        String[] s2 = norm(ps);

        if (s1.length != s2.length) {
            return false;
        }
        for (int i = 0; i < s1.length; i++) {
            if (!s1[i].equals(s2[i])) {
                return false;
            }
        }
        return true;
    }

    private static String[] norm(String[] strs) {
        Map<String, StringBuilder> map = new HashMap<>();
        List<StringBuilder> list = new LinkedList<>();
        for (int i = 0; i < strs.length; i++) {
            StringBuilder sb = map.get(strs[i]);
            if (sb == null) {
                sb = new StringBuilder(String.valueOf(i));
                map.put(strs[i], sb);
                list.add(sb);
            } else {
                sb.append(i);
            }
        }
        String[] ret = new String[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i).toString();
        }
//        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String matcher = sc.nextLine();
            while (true) {
                String pattern = sc.nextLine();
                System.out.println(match(matcher, pattern));
            }
        }
    }
}

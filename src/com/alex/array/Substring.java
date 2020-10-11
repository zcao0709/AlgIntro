package com.alex.array;

/**
 * Created by caozhennan on 2020/7/27 10:14 上午.
 */
public class Substring {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        int ti = 0;
        int found = 0;
        for (int si = 0; si < s.length(); si++) {
            char sc = s.charAt(si);
            for (; ti < t.length(); ti++) {
                if (sc == t.charAt(ti)) {
                    found++;
                    ti++;
                    break;
                }
            }
        }
        return found == s.length();
    }

    public static void main(String[] args) {
        System.out.println(new Substring().isSubsequence("abc", "ahbgdc"));
        System.out.println(new Substring().isSubsequence("axc", "ahbgdc"));
    }
}

package com.alex.array;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/18 10:02 上午.
 *
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class Interleave {
    private static class Snap {
        int i;
        int j;
        int k;

        public Snap(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }
        @Override
        public String toString() {
            return String.format("%d/%d/%d", i, j, k);
        }
    }
    List<Snap> stack = new LinkedList<>();
    Set<String> bad = new HashSet<>();

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        for (int k = 0; k < s3.length(); k++) {
//            System.out.println("1:" + s1.substring(i));
//            System.out.println("2:" + s2.substring(j));
//            System.out.println("3:" + s3.substring(k));
//            System.out.println(Arrays.toString(stack.toArray(new Snap[0])));
//            System.out.println();

            boolean found = false;

            if (i == s1.length() && s3.substring(k).equals(s2.substring(j))) {
                return true;
            } else if (j == s2.length() && s3.substring(k).equals(s1.substring(i))) {
                return true;
            } else if (i != s1.length() && j != s2.length()) {
                if (s1.charAt(i) != s2.charAt(j)) {
                    if (s3.charAt(k) == s1.charAt(i)) {
                        i++;
                        found = true;
                    } else if (s3.charAt(k) == s2.charAt(j)) {
                        j++;
                        found = true;
                    }
                } else if (s1.charAt(i) == s3.charAt(k)) {
                    Snap s = new Snap(i, j, k);
                    if (!bad.contains(s.toString())) {
                        stack.add(0, s);
                        i++;
                        found = true;
                    }
                }
            }
            if (!found) {
                if (stack.isEmpty()) {
                    return false;
                }
                Snap s = stack.remove(0);
                bad.add(s.toString());
                i = s.i;
                j = s.j;
                k = s.k;
                j++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s1 = "aabcc";
//        String s2 = "dbbca";
//        String s3 = "aadbbcbcac";
        String s1 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
        String s2 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
        String s3 = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
        System.out.println(new Interleave().isInterleave(s1, s2, s3));
    }
}

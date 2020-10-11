package com.alex.array;

/**
 * Created by caozhennan on 2020/8/10 10:17 上午.
 */
public class BinarySubString {
    public int countBinarySubstrings(String s) {
        if (s.length() <= 1) {
            return 0;
        }
        int ret = 0;
        char curr = s.charAt(0);
        char next = curr == '0' ? '1' : '0';
        int currNum = 1;
        int nextNum = 0;
        boolean changed = false;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == curr) {
                if (changed) {
                    ret += Math.min(currNum, nextNum);
                    curr = next;
                    next = s.charAt(i);
                    currNum = nextNum;
                    nextNum = 1;
                } else {
                    currNum++;
                }
            } else {
                nextNum++;
                changed = true;
            }
        }
        ret += Math.min(currNum, nextNum);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new BinarySubString().countBinarySubstrings("00110011"));
        System.out.println(new BinarySubString().countBinarySubstrings("10101"));
    }
}

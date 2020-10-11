package com.alex.array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2020/8/3 9:24 上午.
 */
public class Strings {
    public String add(String num1, String num2) {
        if (num1.equals("0")) {
            return num2;
        } else if (num2.equals("0")) {
            return num1;
        }
        StringBuilder ret = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            ret.insert(0, sum%10);
            i--;
            j--;
        }
        return ret.toString();
    }

    public String multiple(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ret = "0";
        String multiplicand = num1.length() >= num2.length() ? num1 : num2;
        String multiplier = multiplicand.equals(num1) ? num2 : num1;

        for (int i = multiplier.length()-1; i >= 0; i--) {
            StringBuilder mid = multiple(multiplicand, multiplier.charAt(i)-'0', multiplier.length()-1-i);
            ret = add(ret, mid.toString());
        }
        return ret;
    }

    private StringBuilder multiple(String num, int factor, int zeros) {
        if (factor == 0) {
            return new StringBuilder("0");
        }
        StringBuilder ret;
        if (factor == 1) {
            ret = new StringBuilder(num);
        } else {
            ret = new StringBuilder();
            int carry = 0;
            for (int i = num.length() - 1; i >= 0; i--) {
                int product = (num.charAt(i) - '0') * factor + carry;
                carry = product / 10;
                ret.insert(0, product % 10);
            }
            if (carry > 0) {
                ret.insert(0, carry);
            }
        }
        for (int i = 0; i < zeros; i++) {
            ret.append("0");
        }
        return ret;
    }

    public boolean isValidBrackets(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.addLast(c);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char last = stack.removeLast();
                if ((c == ')' && last != '(') || (c == '}' && last != '{') || (c == ']' && last != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean repeatedSubstringPattern(String s) {
        if (s.length() <= 1) {
            return false;
        }
        for (int i = 1; i <= s.length()/2; i++) {
            if (s.length() % i != 0) {
                continue;
            }
            String sub = s.substring(0, i);
            int j = i;
            for (; j < s.length(); j += i) {
                if (!sub.equals(s.substring(j, j+i))) {
                    break;
                }
            }
            if (j >= s.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new Strings().add("9", "99"));
//        System.out.println(new Strings().multiple("11", "99"))
//        System.out.println(new Strings().isValidBrackets("([)]"));
        System.out.println(new Strings().repeatedSubstringPattern("abcabc"));
    }
}

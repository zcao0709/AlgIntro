package com.alex.recursion;

import java.util.*;

/**
 * Created by caozhennan on 2020/8/26 10:12 上午.
 */
public class PhoneNumbers {
    public List<String> letterCombinations2(String digits) {
        Map<String, String> board = new HashMap<>();
        board.put("2", "abc");
        board.put("3", "def");
        board.put("4", "ghi");
        board.put("5", "jkl");
        board.put("6", "mno");
        board.put("7", "pqrs");
        board.put("8", "tuv");
        board.put("9", "wxyz");

        List<String> ret = new LinkedList<>();
        if (digits.length() > 0) {
            List<String> chars = new ArrayList<>(digits.length());
            for (int i = 0; i < digits.length(); i++) {
                chars.add(board.get(digits.substring(i, i + 1)));
            }
            letterCombinations(ret, chars, new StringBuilder(), 0);
        }
        return ret;
    }

    private void letterCombinations(List<String> ret, List<String> chars, StringBuilder sb, int i) {
        if (i == chars.size()) {
            ret.add(sb.toString());
            return;
        }
        for (int j = 0; j < chars.get(i).length(); j++) {
            sb.append(chars.get(i).charAt(j));
            letterCombinations(ret, chars, sb, i+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    String[] board = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> ret = new LinkedList<>();
        if (digits.length() > 0) {
            letterCombinations(ret, board, digits, new StringBuilder(), 0);
        }
        return ret;
    }

    private void letterCombinations(List<String> ret, String[] board, String digits, StringBuilder sb, int i) {
        if (i == digits.length()) {
            ret.add(sb.toString());
            return;
        }
        String cs = board[digits.charAt(i)-'2'];
        for (int j = 0; j < cs.length(); j++) {
            sb.append(cs.charAt(j));
            letterCombinations(ret, board, digits, sb, i+1);
            sb.deleteCharAt(i);
        }
    }

    public static void main(String[] args) {
        System.out.println(new PhoneNumbers().letterCombinations("23"));
    }
}

package com.alex.misc;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by caozhennan on 2018/1/26.
 */
public class RemoveSubstring {

    public static String remove(String orig, String sub) {
        Stack<Character> s = new Stack<>();
        Stack<Character> aux = new Stack<>();

        for (int i = 0; i < orig.length(); i++) {
            s.push(orig.charAt(i));

            int j = sub.length() - 1;
            while (!s.isEmpty() && j >= 0) {
                char c = s.peek();
                if (c != sub.charAt(j)) {
                    break;
                }
                aux.push(s.pop());
                j--;
            }
            if (j >= 0) {
                while (!aux.isEmpty()) {
                    s.push(aux.pop());
                }
            } else {
                aux.clear();
            }
        }
        StringBuilder sb = new StringBuilder(s.size());
        while (!s.isEmpty()) {
            sb.insert(0, s.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String orig = sc.nextLine();
        String sub = sc.nextLine();
        System.out.printf(remove(orig, sub));
    }
}

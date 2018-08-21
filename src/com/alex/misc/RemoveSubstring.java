package com.alex.misc;

import java.util.LinkedList;
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
//        System.out.printf(remove(orig, sub));
        System.out.printf(removeSubString(orig, sub));
    }

    private static String removeSubString(String ori, String sub) {
        char[] arr = ori.toCharArray();
        LinkedList<char[]> list = new LinkedList<char[]>();
        for(char ch:arr) {
            list.add(new char[] {ch});
        }

        StringBuilder leftString = new StringBuilder();
        char[] brr = sub.toCharArray();
        int subLen = brr.length;
        int i = 0;
        StringBuilder tmpString = new StringBuilder();
        int tmpLen;
        while(list.size() > 0) {
            char c = list.poll()[0];
            System.out.println("char " + c);
            if(c == brr[i%subLen]) {
                tmpString.append(c);
                tmpLen = tmpString.length();
//                System.out.println("=== 1 tmpString = " + tmpString);
                i++;
                if(i == subLen) {
//                    System.out.println("should remove this sub string");
                    tmpString.delete(tmpLen - subLen, tmpLen);
//                    System.out.println("add tmpString back to list");
                    char[] tmp = tmpString.toString().toCharArray();
                    for(int j = tmp.length - 1; j >= 0; j--) {
                        list.addFirst(new char[] {tmp[j]});
                    }
                    i = 0;
                }
            } else {
                if( i == 0) {
                    leftString.append(c);
                    continue;
                } else {
                    //System.out.println("add " + c + " back to list");
                    list.addFirst(new char[] {c});
                    i = 0;
                }
            }
        }
        System.out.println("leftString = " + leftString); // print left string after removing
        return sub;
    }
}

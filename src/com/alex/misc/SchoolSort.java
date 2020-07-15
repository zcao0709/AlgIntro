package com.alex.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2018/6/20.
 */
public class SchoolSort {
    public static void main(String[] args) {
        String[] array = new String[] { "A", "B", "C", "D", "E" };
        ArrayList<String> list = new ArrayList<>();
        getAllList(list, Arrays.asList(array), "");
        printMatchList(list);
    }

    private static void getAllList(ArrayList<String> list, List<String> candidate, String prefix) {
        //System.out.print(prefix + " , ");
        //System.out.println(Arrays.toString(candidate.toArray()));
        if (prefix.length() == 5) {
            list.add(prefix);
            //System.out.println();
        }
        for (int i = 0; i < candidate.size(); i++) {
            List<String> tmp = new LinkedList<String>(candidate);
            getAllList(list, tmp, prefix + tmp.remove(i));
        }
    }

    private static void printMatchList(ArrayList<String> list) {
        for(String s : list) {
            if(matchRules(s)) {
                System.out.println(s);
            }
        }
    }

    private static boolean matchRules(String s) {

        int aIndex = s.indexOf('A');
        int bIndex = s.indexOf('B');
        int cIndex = s.indexOf('C');
        int dIndex = s.indexOf('D');
        int eIndex = s.indexOf('E');

        StringBuilder right = new StringBuilder();

        if(eIndex == 0) {
            right.append("A");
        }
        if(bIndex == 1) {
            right.append("B");
        }
        if(aIndex == 4) {
            right.append("C");
        }
        if(cIndex != 0) {
            right.append("D");
        }
        if(dIndex == 0) {
            right.append("E");
        }

        if(right.length() == 2) {
            String rightS = right.toString();
            return rightS.indexOf(s.charAt(0)) >= 0 && rightS.indexOf(s.charAt(1)) >= 0;
        }
        return false;
    }
}

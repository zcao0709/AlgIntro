package com.alex.misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2017/12/26.
 */
// A、B、C、D、E五个学校，A说E是第一，B说B是第二，C说A是最差的，D说C不是最好的，E说D是最好的。只有第一和第二名说的是对的，其他说的都是错的，请编程确定五个学校的名次。
public class School {

    private static Word a = v -> v.get(0).equals("E") && v.get(1).equals("A") ? 1 : 0;
    private static Word b = v -> v.get(1).equals("B") ? 1 : 0;
    private static Word c = v -> v.get(4).equals("A") && (v.get(0).equals("C") || v.get(1).equals("C")) ? 1 : 0;
    private static Word d = v -> !v.get(0).equals("C") && (v.get(0).equals("D") || v.get(1).equals("D")) ? 1 : 0;
    private static Word e = v -> v.get(0).equals("D") && v.get(1).equals("E") ? 1 : 0;

    private static List<Word> words = new ArrayList<>(5);
    private static List<String> schools = new ArrayList<>(5);
    static {
        words.add(a);
        words.add(b);
        words.add(c);
        words.add(d);
        words.add(e);
        schools.add("A");
        schools.add("B");
        schools.add("C");
        schools.add("D");
        schools.add("E");
    }

    public interface Word {
        int say(List<String> orders);
    }

    private static List<String> one = new LinkedList<>();

    public static void mutate() {
        if (one.size() == schools.size()) {
            int val = 0;
            for (Word w : words) {
                val += w.say(one);
            }
            if (val == 2) {
                for (int i = 0; i < one.size(); i++)
                    System.out.print(one.get(i) + " ");
                System.out.println();
            }
            return;
        }
        for (int i = 0; i < schools.size(); i++) {
            if (one.contains(schools.get(i))) {
                continue;
            }
            one.add(schools.get(i));
            mutate();
            one.remove(one.size() - 1);
        }
    }

    public static void main(String args[]) {
        mutate();
    }
}

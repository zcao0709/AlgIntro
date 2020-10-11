package com.alex.misc;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by caozhennan on 2020/9/28 1:50 下午.
 * D Q N A L D
 *+G E R A L D
 *=R Q B E R T
 */
public class NumPuzzle {
    interface Cond { boolean satisfy(int n); }
    private static Set<Integer> used = new HashSet<>();
    private static List<Integer> unsed(Cond c) {
        List<Integer> ret = new LinkedList<>();
        IntStream.range(0, 10).filter(v -> !used.contains(v) && (c == null || c.satisfy(v))).forEach(ret::add);
        return ret;
    }
    public static void main(String[] args) {
        for (int e : new int[]{9, 0}) {
            used.add(e);
            int a, cnr, cll, caa, cqe;
            if (e == 0) {
                a = 5;
                cnr = 0; cll = 0; caa = 1; cqe = 0;
            } else {
                a = 4;
                cnr = 1; cll = 1; caa = 0; cqe = 1;
            }
            used.add(a);
            for (int d : unsed(v -> v >= 1 && v <= 8)) {
                int t = (d + d) % 10;
                if (used.contains(t)) {
                    continue;
                }
                used.add(d);
                used.add(t);
                int cdd = (d + d) / 10;
                for (int l : unsed(cll == 0 ? v -> v < 5 : v -> v >= 5)) {
                    int r = (l + l + cdd) % 10;
                    if (used.contains(r)) {
                        continue;
                    }
                    used.add(l);
                    used.add(r);
                    for (int n : unsed(cnr == 0 ? v -> v + r + caa < 10 : v -> v + r + caa >= 10)) {
                        int b = (n + r + caa) % 10;
                        if (b == n || used.contains(b)) {
                            used.remove(n);
                            continue;
                        }
                        used.add(n);
                        used.add(b);
                        for (int q : unsed(e == 0 ? null : v -> v > 0)) {
                            used.add(q);
                            List<Integer> gs = unsed(null);
                            for (int g : gs) {
                                if (d + g + cqe == r) {
                                    System.out.println(d);
                                    return;
                                }
                            }
                            used.remove(q);
                        }
                        used.remove(b);
                        used.remove(n);
                    }
                    used.remove(l);
                    used.remove(r);
                }
                used.remove(t);
                used.remove(d);
            }
            used.remove(a);
            used.remove(e);
        }
    }
}

package com.alex.misc;

import java.util.*;

/**
 * Created by caozhennan on 2020/6/6 4:41 下午.
 */
public class UpperLineCounter {
    private static class UpperLine {
        Map<Integer, List<Integer>> table;
        UpperLine(int[] num) {
            HashMap<Integer, List<Integer>> t = new HashMap<>();
            for (int i = 0; i < num.length; i++) {
                List<Integer> idx = t.computeIfAbsent(num[i], k -> new LinkedList<>());
                idx.add(i);
            }
            System.out.println(t);
            table = t;
        }

        List<Integer> findN(int n) {
            return table.get(n);
        }
    }

    private static class Actions {
        List<Action> actions = new LinkedList<>();

        Actions() {}
        void add(Action action) {
            for (int i = 0; i < actions.size(); i++) {
                Action a = actions.get(i);
                if (a.n == action.n) {
                    a.c += action.c;
                    return;
//                } else if (a.n < action.n) {
//                    actions.add(i, action);
//                    return;
                }
            }
            actions.add(action);
        }
        boolean isEmpty() {
            return actions.isEmpty();
        }
        Action remove() {
            return actions.remove(0);
        }
        public String toString() {
            return Arrays.toString(actions.toArray(new Action[0]));
        }
    }

    private static class Action {
        int n;
        int c;

        Action(int n, int c) {
            this.n = n;
            this.c = c;
        }
        boolean merge(Action action) {
            if (this.n == action.n) {
                this.c += action.c;
                return true;
            }
            return false;
        }
        public String toString() {
            return n + ":" + c;
        }
    }

    private static int findN(int n, int[] num) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] == n) {
                return i;
            }
        }
        return -1;
    }

    private static int count(int n, int[] num) {
        int ret = 0;
        for (int i : num) {
            if (n == i) {
                ret++;
            }
        }
        return ret;
    }

    public static int[] count(int[] num) {
        int[] ret = new int[num.length];
        UpperLine upper = new UpperLine(num);

        Actions actions = new Actions();
        actions.add(new Action(0, num.length));

//        int k = 0;
        while (!actions.isEmpty()) {
            Action action = actions.remove();
            if (action.c == 0) {
                continue;
            }
//            int i = findN(action.n, num);
            List<Integer> idx = upper.findN(action.n);
            if (idx == null || idx.isEmpty()) {
                continue;
            }
            int old = ret[idx.get(0)];
            for (int i : idx) {
                ret[i] += action.c;
            }
            actions.add(new Action(ret[idx.get(0)], idx.size()));
            actions.add(new Action(old, -idx.size()));
            System.out.printf("%d: %s, %s\n", action.n, Arrays.toString(ret), actions);
//            k++;
//            if (k == 30) {
//                break;
//            }
        }
        return ret;
    }

    /*public static int[] count(int[] num) {
        int[] ret = new int[num.length];
        List<Integer> change = new LinkedList<>();
        change.add(0);
        while (!change.isEmpty()) {
            int n = change.remove(0);
            int i = findN(n, num);
            if (i == -1) {
                continue;
            }
            int old = ret[i];
            int now = count(n, ret);
            if (old == now) {
                continue;
            }
            ret[i] = now;
//            if (old == n) {
//                ret[i]--;
//            } else {
//            }
//            if (old == ret[i]) {
//                return ret;
//            }
            change.add(ret[i]);
            change.add(old);
            System.out.printf("%d: %s, %s\n", n, Arrays.toString(ret), Arrays.toString(change.toArray(new Integer[0])));
        }
        return ret;
    }*/

    public static void main(String[] args) {
        int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 0};
        System.out.println(Arrays.toString(count(num)));
    }
}

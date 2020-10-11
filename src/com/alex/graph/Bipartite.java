package com.alex.graph;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/16 2:56 下午.
 */
public class Bipartite {
    private static int UNCOLOR = 0;
    private static int RED = 1;
    private static int GREEN = 2;
    private int[] colors;

    public boolean isBipartite(int[][] graph) {
        colors = new int[graph.length];
        Arrays.fill(colors, UNCOLOR);
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == UNCOLOR) {
                if (colorWithDFS(i, RED, graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    // true: fail to color
    private boolean colorWithDFS(int n, int color, int[][] graph) {
        colors[n] = color;
        int anotherColor = color == RED ? GREEN : RED;

        for (int i : graph[n]) {
            if (colors[i] == UNCOLOR) {
                if (colorWithDFS(i, anotherColor, graph)) {
                    return true;
                }
            } else if (colors[i] != anotherColor) {
                return true;
            }
        }
        return false;
    }

    HashSet<Integer> first = new HashSet<>();
    HashSet<Integer> second = new HashSet<>();

    public boolean isBipartite2(int[][] graph) {
        Set<Integer> handled = new HashSet<>();
        List<Integer> left = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0) {
                continue;
            }
            int r = addToSet(i, graph[i]);
            if (r == -1) {
                return false;
            } else if (r == 1) {
                left.add(i);
            }
        }
        if (checkAndClear()) {
            return false;
        }
        while (!left.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            for (int i : left) {
                int r = addToSet(i, graph[i]);
                if (r == -1) {
                    return false;
                } else if (r == 1) {
                    tmp.add(i);
                }
            }
            left = tmp;
            if (checkAndClear()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAndClear() {
        for (int f : first) {
            if (second.contains(f)) {
                return true;
            }
        }
        first.clear();
        second.clear();
        return false;
    }

    private int addToSet(int point, int[] neighbours) {
        Set<Integer> pSet = null;
        Set<Integer> nSet = null;
        if (first.contains(point) && second.contains(point)) {
            return -1;
        } else if (first.size() == 0 && second.size() == 0) {
            first.add(point);
            for (int n : neighbours) {
                second.add(n);
            }
            return 0;
        } else if (first.contains(point)) {
            pSet = first;
            nSet = second;
        } else if (second.contains(point)) {
            nSet = first;
            pSet = second;
        }
        if (pSet == null || nSet == null) {
            return 1;
        }
        for (int n : neighbours) {
            nSet.add(n);
        }
        return 0;
    }

    public static void main(String[] args) {
//        int[][] graph = new int[][]{{1,2,3}, {0,2}, {0,1,3}, {0,2}};
        int[][] graph = new int[][]{{1},{0},{4},{4},{2,3}};
        Bipartite b = new Bipartite();
        System.out.println(b.isBipartite(graph));
        System.out.println(b.first);
        System.out.println(b.second);
    }
}

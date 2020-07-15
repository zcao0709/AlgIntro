package com.alex.array;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2020/7/14 9:37 上午.
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 */
public class MinimumTotalInTriangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] mid = new int[triangle.get(triangle.size()-1).size()];
        for (int i = 0; i < triangle.get(triangle.size()-1).size(); i++) {
            mid[i] = triangle.get(triangle.size()-1).get(i);
        }
        for (int i = triangle.size()-1; i > 0; i--) {
            for (int j = 0; j < triangle.get(i).size()-1; j++) {
                mid[j] = Math.min(mid[j], mid[j+1]) + triangle.get(i-1).get(j);
            }
        }
        return mid[0];
    }

    public int minimumTotal4(List<List<Integer>> triangle) {
        for (int i = triangle.size()-1; i > 0; i--) {
            for (int j = 0; j < triangle.get(i).size()-1; j++) {
                int curr = Math.min(triangle.get(i).get(j), triangle.get(i).get(j+1));
                triangle.get(i-1).set(j, triangle.get(i-1).get(j)+curr);
            }
        }
        return triangle.get(0).get(0);
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        for (int level = 0; level < triangle.size()-1; level++) {
            for (int index = 0; index < triangle.get(level).size(); index++) {
                int curr = triangle.get(level).get(index);

                if (index == triangle.get(level).size()-1) {
                    triangle.get(level+1).set(index+1, triangle.get(level+1).get(index+1) + curr);
                }
                if (index-1 >= 0) {
                    int left = triangle.get(level).get(index-1);
                    curr = Math.min(left, curr);
                }
                triangle.get(level+1).set(index, triangle.get(level+1).get(index)+curr);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int n : triangle.get(triangle.size()-1)) {
            min = Math.min(min, n);
        }
        return min;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        Deque<Node> queue = new LinkedList<>();
        int level = 0;
        queue.add(new Node(triangle.get(0).get(0), 0, 0));

        while (level < triangle.size()-1) {
            Deque<Node> tmp = new LinkedList<>();

            for (Node node : queue) {
                List<Node> ns = node.neighbours(triangle);
                for (Node n : ns) {
                    n.val += node.val;
                }
                if (tmp.isEmpty()) {
                    tmp.addAll(ns);
                } else {
                    if (tmp.getLast().val > ns.get(0).val) {
                        tmp.getLast().val = ns.get(0).val;
                    }
                    if (ns.size() > 1) {
                        tmp.add(ns.get(1));
                    }
                }
            }
            level++;
            queue = tmp;
        }
        int min = Integer.MAX_VALUE;
        for (Node n : queue) {
            min = Math.min(min, n.val);
        }
        return min;
    }

    private class Node {
        int val;
        int level;
        int index;

        public Node(int val, int level, int index) {
            this.val = val;
            this.level = level;
            this.index = index;
        }

        public List<Node> neighbours(List<List<Integer>> triangle) {
            List<Node> ret = new ArrayList<>(2);
            if (level+1 < triangle.size()) {
                if (index < triangle.get(level+1).size()) {
                    ret.add(new Node(triangle.get(level+1).get(index), level+1, index));
                }
                if (index+1 < triangle.get(level+1).size()) {
                    ret.add(new Node(triangle.get(level+1).get(index+1), level+1, index+1));
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(1);
        l1.add(2);
        List<Integer> l2 = new ArrayList<>(2);
        l2.add(3);
        l2.add(4);
        List<Integer> l3 = new ArrayList<>(3);
        l3.add(6);
        l3.add(5);
        l3.add(7);
        List<Integer> l4 = new ArrayList<>(4);
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);

        List<List<Integer>> t = new ArrayList<>();
        t.add(l1);
        t.add(l2);
        t.add(l3);
        t.add(l4);

        MinimumTotalInTriangle m = new MinimumTotalInTriangle();
        System.out.println(m.minimumTotal(t));
    }
}

package com.alex.graph;

import java.util.*;

/**
 * Created by caozhennan on 2020/8/4 9:28 上午.
 *
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 */
public class ClassSchedule {
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    HashMap<Integer, List<Integer>> classes = new HashMap<>();
    Set<Integer> cache = new HashSet<>();
    Set<Integer> viewed = new HashSet<>();

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        preHandle(prerequisites);
        for (int i = 0; i < prerequisites.length; i++) {
            int curr = prerequisites[i][0];
            int next = prerequisites[i][1];
            if (curr == next) {
                return false;
            }
            cache.add(curr);
            cache.add(next);
            if (!canFinishDFS(next)) {
                return false;
            }
            viewed.addAll(cache);
            cache.clear();
        }
        return true;
    }

    private boolean canFinishDFS(int next) {
        if (viewed.contains(next)) {
            return true;
        }
        System.out.println("next: " + next);
        List<Integer> pre = classes.get(next);
        if (pre == null) {
            return true;
        }
        for (int c : pre) {
            System.out.println("c: " + c);
            if (cache.contains(c)) {
                return false;
            }
            cache.add(c);
            if (!canFinishDFS(c)) {
                return false;
            }
            viewed.add(c);
            cache.remove(c);
        }
        return true;
    }

    private void preHandle(int[][] prerequisites) {
        for (int[] line : prerequisites) {
            List<Integer> pre = classes.computeIfAbsent(line[0], k -> new LinkedList<>());
            pre.add(line[1]);
        }
        System.out.println(classes);
    }

    public static void main(String[] args) {
        int[][] prerequisites = {
                {1, 0},
                {0, 2},
                {2, 1},
        };
        System.out.println(new ClassSchedule().canFinish(3, prerequisites));
    }
}

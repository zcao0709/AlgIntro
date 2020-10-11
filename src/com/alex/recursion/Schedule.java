package com.alex.recursion;

import java.util.*;

/**
 * Created by caozhennan on 2020/8/27 10:34 上午.
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 *
 * 说明:
 *
 *     如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 *     所有的机场都用三个大写字母表示（机场代码）。
 *     假定所有机票至少存在一种合理的行程。
 *
 * 示例 1:
 *
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * 示例 2:
 *
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 */
public class Schedule {
    private boolean found = false;

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> fromTo = new HashMap<>();
        for (List<String> ft : tickets) {
            List<String> to = fromTo.computeIfAbsent(ft.get(0), k -> new LinkedList<>());
            int i = 0;
            for (; i < to.size(); i++) {
                if (to.get(i).compareTo(ft.get(1)) > 0) {
                    break;
                }
            }
            to.add(i, ft.get(1));
        }
        int expected = tickets.size() + 1;
        List<String> ret = new LinkedList<>();
        ret.add("JFK");
        findItinerary(ret, fromTo, expected);
        return ret;
    }

    private void findItinerary(List<String> ret, Map<String, List<String>> fromTo, int expected) {
        if (ret.size() == expected) {
            found = true;
            return;
        }
        String curr = ret.get(ret.size()-1);
        List<String> tos = fromTo.get(curr);
        if (tos == null || tos.size() == 0) {
            return;
        }
        for (int i = 0; i < tos.size(); i++) {
            ret.add(tos.remove(i));
            findItinerary(ret, fromTo, expected);
            if (!found) {
                tos.add(i, ret.remove(ret.size() - 1));
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new LinkedList<>();
//        String[] t1 = new String[]{"MUC", "LHR"};
//        String[] t2 = new String[]{"JFK", "MUC"};
//        String[] t3 = new String[]{"SFO", "SJC"};
//        String[] t4 = new String[]{"LHR", "SFO"};
        String[] t1 = new String[]{"JFK", "SFO"};
        String[] t2 = new String[]{"JFK", "ATL"};
        String[] t3 = new String[]{"SFO", "ATL"};
        String[] t4 = new String[]{"ATL", "JFK"};
        String[] t5 = new String[]{"ATL", "SFO"};
        tickets.add(Arrays.asList(t1));
        tickets.add(Arrays.asList(t2));
        tickets.add(Arrays.asList(t3));
        tickets.add(Arrays.asList(t4));
        tickets.add(Arrays.asList(t5));

        System.out.println(Arrays.toString(new Schedule().findItinerary(tickets).toArray(new String[0])));
    }
}

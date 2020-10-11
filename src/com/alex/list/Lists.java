package com.alex.list;

import java.util.*;

/**
 * Created by caozhennan on 2020/8/25 10:21 上午.
 */
public class Lists {
    Set<String> cache = new HashSet<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ret = new LinkedList<>();

        for (int i = 0; i < nums.length-1; i++) {
            List<Integer> index = new LinkedList<>();
            index.add(i);
            List<List<Integer>> rs = findSubsequences(nums, index);
            while (!rs.isEmpty()) {
                List<Integer> r = rs.remove(0);
                if (cacheIndex(nums, r)) {
                    ret.add(r);
                    rs.addAll(findSubsequences(nums, r));
                }
            }
        }
        for (List<Integer> r : ret) {
            for (int i = 0; i < r.size(); i++) {
                r.set(i, nums[r.get(i)]);
            }
        }
        return ret;
    }

    private boolean cacheIndex(int[] nums, List<Integer> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(nums[list.get(i)]);
        }
        sb.append("]");
        return cache.add(sb.toString());
    }

    private List<List<Integer>> findSubsequences(int[] nums, List<Integer> index) {
        List<List<Integer>> ret = new LinkedList<>();
        int start = index.get(index.size()-1);
        int min = nums[start];
        for (int i = start+1; i < nums.length; i++) {
            if (nums[i] >= min) {
                index.add(i);
                List<Integer> r = new LinkedList<>(index);
                ret.add(r);

                index.remove(index.size()-1);
            }
        }
        return ret;
    }

    /*private boolean exist(int[] nums, List<List<Integer>> ret, List<Integer> newed) {
        if (ret.isEmpty()) {
            return false;
        }
        for (List<Integer> r : ret) {
            if (r.size() == newed.size()) {
                boolean same = true;
                for (int i = 0; i < r.size(); i++) {
                    if (nums[r.get(i)] != nums[newed.get(i)]) {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    return true;
                }
            }
        }
        return false;
    }*/

    public static void main(String[] args) {
//        int[] nums = new int[]{4, 6, 7, 7};
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        List<List<Integer>> ret = new Lists().findSubsequences(nums);
        for (List<Integer> r : ret) {
            System.out.println(Arrays.toString(r.toArray(new Integer[0])));
        }
    }
}

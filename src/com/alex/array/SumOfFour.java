package com.alex.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2020/10/5 4:20 下午.
 */
public class SumOfFour {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new LinkedList<>();
        List<Integer> one = new ArrayList<>(4);
        fourSumR(nums, target, 0, 0, one, ret);
        return ret;
    }

    private void fourSumR(int[] nums, int target, int curr, int start, List<Integer> one, List<List<Integer>> ret) {
        if (one.size() == 4) {
            if (curr == target) {
                List<Integer> clone = new ArrayList<>(one);
                ret.add(clone);
            }
            return;
        }
        if (start + (4-one.size()) > nums.length) {
            return;
        }
        if (one.size() == 3) {
            if (curr + nums[nums.length - 1] < target) {
                return;
            }
        }
        int last = 0;
        for (int i = start; i < nums.length; i++) {
            if (i == start || nums[i] != last) {
                one.add(nums[i]);
                int size = one.size();
                fourSumR(nums, target, curr + nums[i], i+1, one, ret);
                last = one.remove(size - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> ret = new SumOfFour().fourSum(nums, 0);
        for (List<Integer> one : ret) {
            System.out.println(Arrays.toString(one.toArray(new Integer[0])));
        }
    }
}

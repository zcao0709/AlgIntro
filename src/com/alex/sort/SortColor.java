package com.alex.sort;

import java.util.Arrays;

/**
 * Created by caozhennan on 2020/10/7 4:10 下午.
 */
public class SortColor {
    public void sortColors(int[] nums) {
        int right0 = -1;
        int right1 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                right0++;
                if (swap(nums, right0, i)) {
                    i--;
                }
            } else if (nums[i] == 1) {
                if (right1 == -1) {
                    right1 = right0 + 1;
                } else {
                    right1++;
                }
                if (swap(nums, right1, i)) {
                    i--;
                }
            }
        }
    }

    private boolean swap(int[] nums, int i, int j) {
        if (i == j) {
            return false;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        return true;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2,0,2,1,1,0};
        int[] nums = new int[]{0};
        new SortColor().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}

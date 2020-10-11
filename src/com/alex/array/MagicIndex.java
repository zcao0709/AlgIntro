package com.alex.array;

import java.util.Arrays;

/**
 * Created by caozhennan on 2020/7/31 10:03 上午.
 */
public class MagicIndex {
    public int findMagicIndex(int[] nums) {
        int pos = Arrays.binarySearch(nums, 0);
        if (pos < 0) {
            pos = -(pos + 1);
        }
        for (; pos < nums.length; pos++) {
            if (nums[pos] == pos) {
                return pos;
            } else {
                pos = nums[pos] - 1;
            }
        }
        return -1;
    }
}

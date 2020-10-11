package com.alex.dp;

/**
 * Created by caozhennan on 2020/9/30 3:55 下午.
 */
public class Rob {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] max = new int[]{nums[0], nums[1], nums[0]+nums[2]};
        for (int i = 3; i < nums.length; i++) {
            int tmp = max[0];
            max[0] = max[1];
            max[1] = max[2];
            max[2] = nums[i] + Math.max(tmp, max[0]);
        }
        return Math.max(max[2], max[1]);
    }
}

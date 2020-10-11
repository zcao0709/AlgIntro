package com.alex.array;

import java.util.*;

/**
 * Created by caozhennan on 2020/7/19 10:36 上午.
 */
public class StabBalloon {
    Integer cachedMax = null;

    private HashMap<String, Integer> cache = new HashMap<>();

    public int maxCoins(int[] nums) {
        List<Integer> ns = new LinkedList<>();
        for (int n : nums) {
            ns.add(n);
        }
//        return maxCoin4Dp(nums);
        return maxCoins3(nums);
//        return maxCoins2(ns, 0);
//        return cachedMax == null ? 0 : cachedMax;
    }

    // O(N!)
    private void maxCoins1(List<Integer> nums, int sum) {
        if (nums.size() == 1) {
            sum += nums.get(0);
            if (cachedMax == null || sum > cachedMax) {
                cachedMax = sum;
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            int m = i-1 >= 0 ? nums.get(i-1) : 1;
            m *= i+1 < nums.size() ? nums.get(i+1) : 1;
            int e = nums.remove(i);

            sum += e * m;
            maxCoins1(nums, sum);
            sum -= e * m;
            nums.add(i, e);
        }
    }

    // O(N!)
    private int maxCoins2(List<Integer> nums, int sum) {
        if (nums.size() == 1) {
            sum += nums.get(0);
            return sum;
        }
        int max = sum;
        for (int i = 0; i < nums.size(); i++) {
            int m = i-1 >= 0 ? nums.get(i-1) : 1;
            m *= i+1 < nums.size() ? nums.get(i+1) : 1;
            int e = nums.remove(i);

            sum += e * m;
            int curr = maxCoins2(nums, sum);
            if (curr > max) {
                max = curr;
            }
            sum -= e * m;
            nums.add(i, e);
        }
        return max;
    }

    // divide-conquer
    private int maxCoins3(int[] nums) {
        int[] ns = new int[nums.length+2];
        ns[0] = 1;
        System.arraycopy(nums, 0, ns, 1, nums.length);
        ns[ns.length-1] = 1;
        int[][] cache = new int[ns.length][ns.length];
        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }
        return maxCoin3Re(ns, 0, ns.length-1, cache);
    }
    private int maxCoin3Re(int[] ns, int left, int right, int[][] cache) {
        System.out.println(left + ", " + right);
        for (int[] c : cache) {
            System.out.println(Arrays.toString(c));
        }
        if (left == right-1) {
            cache[left][right] = 0;
            return 0;
        }
        if (cache[left][right] != -1) {
            return cache[left][right];
        }
        for (int k = left + 1; k < right; k++) {
            int sum = ns[left] * ns[k] * ns[right];
            sum += maxCoin3Re(ns, left, k, cache) + maxCoin3Re(ns, k, right, cache);
            cache[left][right] = Math.max(cache[left][right], sum);
        }
        return cache[left][right];
    }

    private int maxCoin4Dp(int[] nums) {
        int[] ns = new int[nums.length+2];
        ns[0] = 1;
        System.arraycopy(nums, 0, ns, 1, nums.length);
        ns[ns.length-1] = 1;
        int[][] dp = new int[ns.length][ns.length];

        for (int i = dp.length-3; i >= 0; i--) {
            for (int j = i+2; j < ns.length; j++) {
                for (int k = i+1; k < j; k++) {
                    int sum = ns[i] * ns[k] * ns[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][ns.length-1];
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{9, 76, 64, 21};
        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println(new StabBalloon().maxCoins(nums));
    }
}

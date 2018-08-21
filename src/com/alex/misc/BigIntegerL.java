package com.alex.misc;

/**
 * Created by caozhennan on 2018/7/21.
 */
public class BigIntegerL {
    private static final int ORDER = 18;
    private static final long LIMIT = 1000000000000000000L;
    private final long[] nums;

    public BigIntegerL(String nums) {
        int size = nums.length() / ORDER + (nums.length() % ORDER == 0 ? 0 : 1);
        this.nums = new long[size];

        int end = nums.length();
        for (int i = 0; i < size; i++) {
            int start = end - ORDER >= 0 ? end - ORDER : 0;
            this.nums[i] = Long.parseLong(nums.substring(start, end));
            end = start;
        }
    }

    private BigIntegerL(long[] nums, int carry) {
        this.nums = new long[nums.length + carry];
        System.arraycopy(nums, 0, this.nums, 0, nums.length);
        if (carry == 1) {
            this.nums[this.nums.length - 1] = carry;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length-1; i++) {
            sb.insert(0, String.format("%0"+ORDER+"d", nums[i]));
        }
        sb.insert(0, String.format("%d", nums[nums.length-1]));
        return sb.toString();
    }

    public BigIntegerL add(BigIntegerL addend) {
        long[] sum = new long[Math.max(this.nums.length, addend.nums.length)];
        int carry = 0;
        for (int i = 0; i < sum.length; i++) {
            long b1 = i >= this.nums.length ? 0 : this.nums[i];
            long b2 = i >= addend.nums.length ? 0 : addend.nums[i];
            sum[i] = b1 + b2 + carry;
            if (sum[i] >= LIMIT) {
                sum[i] -= LIMIT;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        return new BigIntegerL(sum, carry);
    }

    public static void main(String[] args) {
        BigIntegerL n1 = new BigIntegerL("9");
        System.out.println(n1.toString());
        BigIntegerL n2 = new BigIntegerL("66666666");
        System.out.println(n2.toString());
        System.out.println(n1.add(n2).toString());
    }
}

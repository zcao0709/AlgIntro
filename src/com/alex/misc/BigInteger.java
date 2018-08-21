package com.alex.misc;

/**
 * Created by caozhennan on 2018/7/21.
 */
public class BigInteger {
    private final byte[] nums;

    public BigInteger(String nums) {
        this.nums = new byte[nums.length()];
        for (int i = 0; i < nums.length(); i++) {
            this.nums[i] = (byte)(nums.charAt(nums.length()-1-i) - '0');
        }
    }

    private BigInteger(byte[] nums, byte carry) {
        this.nums = new byte[nums.length + carry];
        System.arraycopy(nums, 0, this.nums, 0, nums.length);
        if (carry == 1) {
            this.nums[this.nums.length - 1] = carry;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : nums) {
            sb.insert(0, i);
        }
        return sb.toString();
    }

    public BigInteger add(BigInteger addend) {
        byte[] sum = new byte[Math.max(this.nums.length, addend.nums.length)];
        byte carry = 0;
        for (int i = 0; i < sum.length; i++) {
            byte b1 = i >= this.nums.length ? 0 : this.nums[i];
            byte b2 = i >= addend.nums.length ? 0 : addend.nums[i];
            sum[i] = (byte)(b1 + b2 + carry);
            if (sum[i] >= 10) {
                sum[i] -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        return new BigInteger(sum, carry);
    }

    public static void main(String[] args) {
        BigInteger n1 = new BigInteger("999999999999999999995");
        System.out.println(n1.toString());
        BigInteger n2 = new BigInteger("5");
        System.out.println(n2.toString());
        System.out.println(n1.add(n2).toString());
        System.out.println(Long.MAX_VALUE);
    }
}

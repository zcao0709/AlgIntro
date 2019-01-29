package com.alex.array;

import java.util.Arrays;

/**
 * Created by caozhennan on 2018/6/30.
 */
public class SubarrayWithMaxSum {

    // programming pearls page78
    public static int[] max1(int[] arr) {
        int[] maxSofar = new int[]{0, -1, -1};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum >= maxSofar[0]) {
                    maxSofar[0] = sum;
                    maxSofar[1] = i;
                    maxSofar[2] = j;
                }
            }
        }
        return maxSofar;
    }

    // programming pearls page79
    public static int[] max2a(int[] arr) {
        int[] maxSofar = new int[]{0, -1, -1};
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum >= maxSofar[0]) {
                    maxSofar[0] = sum;
                    maxSofar[1] = i;
                    maxSofar[2] = j;
                }
            }
        }
        return maxSofar;
    }

    // programming pearls page79
    public static int[] max2b(int[] arr) {
        int[] maxSofar = new int[]{0, -1, -1};
        int[] partsum = new int[arr.length+1];
        partsum[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            partsum[i+1] = partsum[i] + arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = partsum[j+1] - partsum[i];
                if (sum >= maxSofar[0]) {
                    maxSofar[0] = sum;
                    maxSofar[1] = i;
                    maxSofar[2] = j;
                }
            }
        }
        return maxSofar;
    }

    // programming pearls page80
    // divide and conquer
    public static int[] max3a(int[] arr) {
        return max3a(arr, 0, arr.length-1);
    }

    private static int[] max3a(int[] arr, int left, int right) {
        int[] ret;
        if (left == right) {
            if (arr[left] >= 0) {
                ret = new int[]{arr[left], left, right};
            } else {
                ret = new int[]{0, -1, -1};
            }
        } else {
            int mid = (left + right) >>> 1;
            int[] lefthalf = max3a(arr, left, mid);
            int[] righthalf = max3a(arr, mid + 1, right);

            int[] withmid = new int[]{0, mid + 1, mid};
            int sum = withmid[0];
            for (int i = mid; i >= left; i--) {
                sum += arr[i];
                if (sum >= withmid[0]) {
                    withmid[0] = sum;
                    withmid[1] = i;
                }
            }
            sum = withmid[0];
            for (int i = mid + 1; i <= right; i++) {
                sum += arr[i];
                if (sum >= withmid[0]) {
                    withmid[0] = sum;
                    withmid[2] = i;
                }
            }
            if (withmid[0] > lefthalf[0] && withmid[0] > righthalf[0]) {
                if (withmid[1] > withmid[2]) {
                    withmid[1] = -1;
                    withmid[2] = -1;
                }
                ret = withmid;
            } else if (lefthalf[0] > righthalf[0]) {
                ret = lefthalf;
            } else {
                ret = righthalf;
            }
        }
        System.out.printf("%d~%d: %s\n", left, right, Arrays.toString(ret));
        return ret;
    }

    // programming pearls page80
    // divide and conquer
    public static int[] max3b(int[] arr) {
        System.out.printf("init: %s\n", Arrays.toString(arr));
        return max3b(arr, 0, arr.length-1).toRet();
    }

    private static final class SubSum implements Comparable<SubSum> {
        final int sum;
        final int left;
        final int right;

        static final SubSum EMPTY = new SubSum(0, -1, -1);

        SubSum(int sum, int left, int right) {
            this.sum = sum;
            this.left = left;
            this.right = right;
        }

        boolean isEmpty() {
            return left == -1 || right == -1;
        }

        @Override
        public int compareTo(SubSum other) {
            if (other.isEmpty()) {
                return 1;
            } else if (isEmpty()) {
                return -1;
            }
            if (sum > other.sum) {
                return 1;
            } else if (sum < other.sum) {
                return -1;
            } else {
                return (right-left) - (other.right-other.left);
            }
        }

        SubSum merge(SubSum onRight) {
            return merge(onRight, false);
        }

        SubSum merge(SubSum onRight, boolean keepNeg) {
            if (right != -1 && onRight.left != -1 && right + 1 != onRight.left) {
                throw new IllegalArgumentException(String.format("not adjacent: %s to %s", this, onRight));
            }
            int s;
            if (onRight.isEmpty()) {
                s = sum;
            } else if (isEmpty()) {
                s = onRight.sum;
            } else {
                s = sum + onRight.sum;
            }
            if (s < 0 && !keepNeg) {
                return EMPTY;
            } else {
                return new SubSum(s, left, onRight.right);
            }
        }

        @Override
        public String toString() {
            return String.format("[%d, %d, %d]", sum, left, right);
        }
    }

    private static final class MidRet {
        final SubSum max;
        final SubSum left;
        final SubSum right;
        final SubSum total;

        public MidRet(SubSum max, SubSum left, SubSum right, SubSum total) {
            this.max = max;
            this.left = left;
            this.right = right;
            this.total = total;
        }

        MidRet(int sum, int left, int right, int leftSum, int toRight, int rightSum, int toLeft, int total, int totalLeft, int totalRight) {
            this.max = new SubSum(sum, left, right);
            this.left = new SubSum(leftSum, totalLeft, toRight);
            this.right = new SubSum(rightSum, toLeft, totalRight);
            this.total = new SubSum(total, totalLeft, totalRight);
        }

        static MidRet one(int value, int index) {
            if (value >= 0) {
                return new MidRet(value, index, index, value, index, value, index, value, index, index);
            } else {
                return zero(value, index);
            }
        }

        static MidRet zero(int total, int index) {
            return new MidRet(SubSum.EMPTY, SubSum.EMPTY, SubSum.EMPTY, new SubSum(total, index, index));
        }

        @Override
        public String toString() {
            return String.format("[max: %s, left: %s, right: %s，total: %s]", max, left, right, total);
        }

        int[] toRet() {
            return new int[]{max.sum, max.left, max.right};
        }
    }

    public static MidRet max3b(int[] arr, int left, int right) {
        MidRet ret;
        if (left == right) {
            ret = MidRet.one(arr[left], left);

        } else {
            int mid = (left + right) >>> 1;
            MidRet lefthalf = max3b(arr, left, mid);
            MidRet righthalf = max3b(arr, mid + 1, right);

            SubSum max = lefthalf.right.merge(righthalf.left);
            if (lefthalf.max.compareTo(max) > 0) {
                max = lefthalf.max;
            }
            if (righthalf.max.compareTo(max) > 0) {
                max = righthalf.max;
            }
            if (right - left == arr.length - 1) {
                ret = new MidRet(max, null, null, null);
            } else {
                SubSum leftsum = lefthalf.total.merge(righthalf.left);
//                System.out.printf("%d~%d: %s\n", left, right, leftsum);
                if (lefthalf.left.compareTo(leftsum) > 0) {
                    leftsum = lefthalf.left;
                }
//                System.out.printf("%d~%d: %s\n", left, right, leftsum);
                SubSum rightsum = lefthalf.right.merge(righthalf.total);
                if (righthalf.right.compareTo(rightsum) > 0) {
                    rightsum = righthalf.right;
                }
                ret = new MidRet(max, leftsum, rightsum, lefthalf.total.merge(righthalf.total, true));
            }
        }
//        System.out.printf("%d~%d: %s\n", left, right, ret);
        return ret;
    }

    // programming pearls page80
    // divide and conquer
    public static int max3aIndexless(int[] arr) {
        return max3aIndexless(arr, 0, arr.length-1);
    }

    private static int max3aIndexless(int[] arr, int left, int right) {
        if (left == right) {
            return Math.max(0, arr[left]);
        }
        int mid = (left + right) >>> 1;
        int leftmax = 0, sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += arr[i];
            leftmax = Math.max(leftmax, sum);
        }
        int rightmax = 0;
        sum = 0;
        for (int i = mid+1; i <= right; i++) {
            sum += arr[i];
            rightmax = Math.max(rightmax, sum);
        }
        return Math.max(leftmax+rightmax, Math.max(max3aIndexless(arr, left, mid), max3aIndexless(arr, mid+1, right)));
    }

    // programming pearls page80
    // divide and conquer
    public static int max3bIndexless(int[] arr) {
        return max3bIndexless(arr, 0, arr.length-1)[0];
    }

    private static int[] max3bIndexless(int[] arr, int left, int right) {
        if (left == right) {
            int v = Math.max(0, arr[left]);
            return new int[]{v, v, v, arr[left]}; // max, left, right, total
        }
        int mid = (left + right) >>> 1;
        int[] lefthalf = max3bIndexless(arr, left, mid);
        int[] righthalf = max3bIndexless(arr, mid+1, right);
        int[] ret = new int[4];
        ret[0] = Math.max(lefthalf[2]+righthalf[1], Math.max(lefthalf[0], righthalf[0]));
        ret[1] = Math.max(lefthalf[1], lefthalf[3]+righthalf[1]);
        ret[2] = Math.max(righthalf[2], righthalf[3]+lefthalf[2]);
        ret[3] = lefthalf[3] + righthalf[3];
        return ret;
    }

    public static int max3bNeg(int[] arr) {
        return max3bNeg(arr, 0, arr.length-1)[0];
    }

    private static int[] max3bNeg(int[] arr, int left, int right) {
        if (left == right) {
            return new int[]{arr[left], arr[left], arr[left], arr[left]}; // max, left, right, total
        }
        int mid = (left + right) >>> 1;
        int[] lefthalf = max3bNeg(arr, left, mid);
        int[] righthalf = max3bNeg(arr, mid+1, right);
        int[] ret = new int[4];
        ret[0] = Math.max(lefthalf[2]+righthalf[1], Math.max(lefthalf[0], righthalf[0]));
        ret[1] = Math.max(lefthalf[1], lefthalf[3]+righthalf[1]);
        ret[2] = Math.max(righthalf[2], righthalf[3]+lefthalf[2]);
        ret[3] = lefthalf[3] + righthalf[3];
//        System.out.printf("%d~%d: %s\n", left, right, Arrays.toString(ret));
        return ret;
    }

    // programming pearls page81
    // dp
    public static int[] max4(int[] arr) {
        int[] maxSofar = new int[]{0, -1, -1};
        int[] maxEndingHere = new int[]{0, -1, -1};

        for (int i = 0; i < arr.length; i++) {
            // invariant:
            // maxEndingHere holds the max sum of sub-array ending at i, including i
            // maxSofar holds the max sum of sub-array of arr[0..i]
            int sum = maxEndingHere[0] + arr[i];
            if (sum >= 0) {
                maxEndingHere[0] = sum;
                if (maxEndingHere[1] == -1) {
                    maxEndingHere[1] = i;
                }
                maxEndingHere[2] = i;
            } else {
                maxEndingHere[0] = 0;
                maxEndingHere[1] = -1;
                maxEndingHere[2] = -1;
            }
//            System.out.printf("ending post: %s\n", Arrays.toString(maxEndingHere));
            if (maxEndingHere[0] >= maxSofar[0]) {
//                System.out.printf("sofar: %s\n", Arrays.toString(maxSofar));
                maxSofar[0] = maxEndingHere[0];
                maxSofar[1] = maxEndingHere[1];
                maxSofar[2] = maxEndingHere[2];
            }
        }
        return maxSofar;
    }

    // programming pearls page81
    public static int max4Indexless(int[] arr) {
        int maxSofar = 0;
        int maxEndingHere = 0;

        for (int i = 0; i < arr.length; i++) {
            maxEndingHere = Math.max(maxEndingHere+arr[i], 0);
            maxSofar = Math.max(maxSofar, maxEndingHere);
        }
        return maxSofar;
    }

    // programming pearls page85 problem9
    public static int max4Neg(int[] arr) {
        int maxSofar = arr[0];
        int maxEndingHere = 0;

        for (int i = 0; i < arr.length; i++) {
            maxEndingHere = Math.max(maxEndingHere+arr[i], arr[i]);
            maxSofar = Math.max(maxSofar, maxEndingHere);
        }
        return maxSofar;
    }
    public static void main(String[] args) {
        int[] arr1 = {-31, -41, -59, -26, -53, -58, -97, -93, -23, -84}; // when all elements are negative, return 0 and [-1, -1]
        System.out.printf("max3b: %s\n", Arrays.toString(max3b(arr1)));
        System.out.printf("max3a: %s\n", max3aIndexless(arr1));
        System.out.printf("max3b: %s\n", max3bIndexless(arr1));
        System.out.printf("max3bNeg: %s\n", max3bNeg(arr1));
        System.out.printf("max4: %d\n", max4Indexless(arr1));
        System.out.printf("max4Neg: %d\n", max4Neg(arr1));
        int[] arr2 = {31, -41, 59, 26, -53, 58, 97, -93, -23, 84};
        System.out.printf("max3b: %s\n", Arrays.toString(max3b(arr2)));
        System.out.printf("max3a: %d\n", max3aIndexless(arr2));
        System.out.printf("max3b: %d\n", max3bIndexless(arr2));
        System.out.printf("max3bNeg: %s\n", max3bNeg(arr2));
        System.out.printf("max4: %d\n", max4Indexless(arr2));
        System.out.printf("max4Neg: %d\n", max4Neg(arr2));
//        int[] arr3 = {-31, 41, 59};
//        System.out.println(Arrays.toString(max3b(arr3)));
//        int[] arr4 = {-31, 0};
//        System.out.println(Arrays.toString(max3b(arr4)));
    }
}

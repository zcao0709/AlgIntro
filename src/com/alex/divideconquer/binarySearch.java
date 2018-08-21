package com.alex.divideconquer;

/**
 * Created by caozhennan on 2018/6/22.
 */
public class binarySearch {
    public static int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int searchLeft(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = -1;
        int right = arr.length - 1;
        while (left + 1 != right) {
            // invariant: arr[left] < target && arr[right] >= target && left < right
            int mid = (left + right) >>> 1;
            if (arr[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // assert: left + 1 == right && arr[left] < target && arr[right] >= target
        if (right >= arr.length - 1 || arr[right] != target) {
            return -1;
        } else {
            return right;
        }
    }

    public static int searchRight(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = -1;
        int right = arr.length - 1;
        while (left + 1 != right) {
            // invariant: arr[left] <= target && arr[right] > target && left < right
            int mid = (left + right) >>> 1;
            if (arr[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // assert: left + 1 == right && arr[left] <= target && arr[right] > target
        if (left < arr.length && arr[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 4, 5, 6, 7};
        System.out.println(search(arr, 1));
        System.out.println(searchLeft(arr, 1));
        System.out.println(searchRight(arr, 1));
    }
}

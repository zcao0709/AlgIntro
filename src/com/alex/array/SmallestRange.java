package com.alex.array;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by caozhennan on 2020/8/1 6:39 下午.
 */
public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        int minRange = right - left;
        int size = nums.size();

        int[] next = new int[size];
        int max = Integer.MIN_VALUE;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(index -> nums.get(index).get(next[index])));
        for (int i = 0; i < size; i++) {
            minHeap.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        while (true) {
            int minIndex = minHeap.poll();
            int min = nums.get(minIndex).get(next[minIndex]);
            int curRange = max - min;
            if (curRange < minRange) {
                minRange = curRange;
                left = min;
                right = max;
            }
            next[minIndex]++;
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }
            minHeap.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{left, right};
    }
}

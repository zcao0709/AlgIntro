package com.alex.hackerrank;

import java.util.*;

/**
 * Created by Administrator on 2016/11/17.
 */
// https://www.hackerrank.com/challenges/largest-rectangle
public class LargestRectangle {
    private static class Area {
        int height;
        int length;

        Area(int height, int length) {
            this.height = height;
            this.length = length;
        }

        long area() {
            return (long)height * length;
        }
        @Override
        public String toString() {
            return height + "," + length;
        }
    }
    public static void main(String[] args) {
        solution2();
    }

    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Area> areas = new LinkedList<>();
        long max = 0;
        for (int i = 0; i < n; i++) {
            int h = sc.nextInt();
            Area curr;
            if (areas.isEmpty()) {
                curr = new Area(h, 1);
            } else {
                int length = 1;
                for (int j = 0; j < areas.size(); j++) {
                    Area a = areas.get(j);
                    if (a.height <= h) {
                        a.length++;
                    }
                    max = Math.max(max, a.area());
                    if (a.height > h) {
                        length = Math.max(length, a.length+1);
                        areas.remove(j);
                        j--;
                    }
                }
                curr = new Area(h, length);
            }
//            max = Math.max(max, curr.area());
            areas.add(curr);
        }
        System.out.println(max);
//        System.out.println(Arrays.toString(areas.toArray(new Area[0])));
        sc.close();
    }

    private static void solution2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        Stack<Integer> s = new Stack<>();
        long max = 0;
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
            while (!s.isEmpty() &&  heights[s.peek()] > heights[i]) {
                int j = s.pop();
                int left = s.isEmpty() ? 0 : s.peek()+1;
                int right = i - 1;
                max = Math.max(max, (right-left+1) * heights[j]);
            }
            s.push(i);
        }
        if (!s.isEmpty()) {
            int right = s.pop();
            max = Math.max(max, heights[right]*1);
            while (!s.isEmpty()) {
                int p = s.pop();
                int left = s.isEmpty() ? 0 : s.peek()+1;
                max = Math.max(max, heights[p]*(right-left+1));
            }
        }
        System.out.println(max);
        sc.close();
    }
}

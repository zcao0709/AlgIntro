package com.alex.misc;

import java.util.*;

/**
 * Created by caozhennan on 2019-03-21 16:16.
 */
/*
After shooting videos for a few months, the team has been left with a large amou content and needs to scale the way they create video scenes.
To help streamline the creation of the final videos, the team wants to develop an automated way of breaking up individual shots
(short sequence from a particular camera angle) in a video into scenes (a sequence of shots). There is already an algorithm that
breaks the video up into shots (short sequences from a particular camera angle) and labels them.
Write a function which will partition a sequence of shot labels into minimal subsequences so that no shot labels appear in more than one subsequence.
The output should be the length of each subsequence.
Input
The input to the function/method consists of an argument - inputList, a list of characters representing the sequence of shots.
Output
Return a list of integers representing the length of each scene, in the order in which it appears in the given sequence of shots.
Examples
Example 1
Input:
inputList = [a, b, c]
Output: [1, 1, 1]
Explanation:
Because there are no recurring shots, all shots can be in the minimal length 1 subsequence.
Example 2
Input:
inputList = [a, b, c, a]
Output: [4]
Explanation:
Because ‘a’ appears more than once, everything between the first and last appearance of ‘a’ must be in the same list.
Example 3:
Input
inputList = [a, b, a, b, c, b, a, c, a, d, e, f, e, g, d, e, h, i, j, h, k, l, i, j]
Output [9, 7, 8]
 */
public class CharacterPartitioner {

    public static List<Integer> partition(char[] intputList) {
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < intputList.length; i++) {
            lastOccurrence.put(intputList[i], i);
        }
        List<Integer> ret = new LinkedList<>();
        int len = 0;
        int stop = 0;
        for (int i = 0; i < intputList.length; i++) {
            len++;
            stop = Math.max(stop, lastOccurrence.get(intputList[i]));
            if (i == stop) {
                ret.add(len);
                len = 0;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(partition(new char[]{}));
        System.out.println(partition(new char[]{'a'}));
        System.out.println(partition(new char[]{'a', 'a'}));
        System.out.println(partition(new char[]{'a', 'b', 'c'}));
        System.out.println(partition(new char[]{'a', 'b', 'c', 'a'}));
        System.out.println(partition(new char[]{'a', 'b', 'c', 'a', 'c', 'b'}));
        System.out.println(partition(new char[]{'a', 'b', 'a', 'b', 'c', 'b', 'a', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h', 'i', 'j', 'h', 'k', 'l', 'i', 'j'}));
    }
}

package com.alex;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Test {
    //import java.io.*;
//import java.util.*;
//import java.text.*;
//import java.math.*;
//import java.util.regex.*;

/*
 * Complete the function below.
 */
    public static String[] a = {"abc", "def", "ghi", "jkl", "mnop", "pqrs", "tvw", "xyz"};
    public static String str = "";

    public static void dfs(int n) {
        if (n == a.length) {
            System.out.println(str);
            return;
        }
        for (int i = 0; i < a[n].length(); i++) {
            str += a[n].charAt(i);
            dfs(n + 1);
            str = str.substring(0, str.length()-1);
        }
    }

    // time complexity: O(cityLength*cityWidth*lockerXCoordinates.length)
    // spatial complexity: O(1), the matrix being returned is not considered.
    static int[][] getLockerDistanceGrid(int cityLength, int cityWidth, int[] lockerXCoordinates, int[] lockerYCoordinates) {
        int[][] ret = new int[cityLength][cityWidth];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[0].length; j++) {
                ret[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int k = 0; k < lockerYCoordinates.length; k++) {
            int x = lockerXCoordinates[k] - 1;
            int y = lockerYCoordinates[k] - 1;
            for (int i = 0; i < ret.length; i++) {
                for (int j = 0; j < ret[0].length; j++) {
                    int d = dist(i, j, x, y);
                    if (d < ret[i][j])
                        ret[i][j] = d;
                }
            }
        }
        return ret;
    }

    static int dist(int x, int y, int ox, int oy) {
        return Math.abs(x-ox) + Math.abs(y-oy);
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
//        Main obj2=null;
//        try{
//            FileInputStream fis=new FileInputStream("dataObject.out");
//            ObjectInputStream ois=new ObjectInputStream(fis);
//            obj2=(Main)ois.readObject();
//            System.out.println(obj2.getI());
//            System.out.println(obj2.getWord());
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//        long[] sum = new long[5];
//        System.out.println(Arrays.toString(sum));
//
//        List<String> lt = new ArrayList<>(10);
//        lt.add("v2");
//        lt.add("v-1");
//        lt.add("2");
//        lt.add("2-3");
//        lt.add("3");
//        System.out.println(lt);
//        Collections.sort(lt);
//        System.out.println(lt);
//
//        int n = 16 - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
//        dfs(0);
//        int arr[] = {10, 9, 8, 7};
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (arr[j] > arr[j+1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));
        int len = 205;
        System.out.printf("%02x\n", len);
        byte bg = (byte) (len & 0xFF00);
        byte lt = (byte) (len & 0xFF);
        System.out.printf("%02x\n", lt);
        System.out.printf("%02x\n", bg);

        int i = (bg << 8) + (lt & 0xFF);
        System.out.printf("%02x\n", bg << 8);
        System.out.printf("%02x\n", i);
    }
}

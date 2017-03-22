package com.alex;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

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

    public static void main(String[] args) {
        Main obj2=null;
        try{
            FileInputStream fis=new FileInputStream("dataObject.out");
            ObjectInputStream ois=new ObjectInputStream(fis);
            obj2=(Main)ois.readObject();
            System.out.println(obj2.getI());
            System.out.println(obj2.getWord());
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}

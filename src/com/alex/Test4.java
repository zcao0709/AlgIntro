package com.alex;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Test4 {
    //import java.io.*;
//import java.util.*;
//import java.text.*;
//import java.math.*;
//import java.util.regex.*;

/*
 * Complete the function below.
 */

    // time complexity: O(cityLength*cityWidth)
    // spatial complexity: O(lockerXCoordinates), the matrix being returned is not considered.
    static int[][] getLockerDistanceGrid(int cityLength, int cityWidth, int[] lockerXCoordinates, int[] lockerYCoordinates) {
        int[][] ret = new int[cityLength][cityWidth];
        Point[] xkeys = new Point[lockerYCoordinates.length];
        Point[] ykeys = new Point[lockerYCoordinates.length];
        for (int k = 0; k < lockerYCoordinates.length; k++) {
            int x = lockerXCoordinates[k] - 1;
            int y = lockerYCoordinates[k] - 1;
            xkeys[k] = new Point(x, y);
            ykeys[k] = new Point(x, y);
            ret[x][y] = 0;
        }
        Comparator yComp = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.y - p2.y;
            }
        };
        Arrays.sort(ykeys, yComp);

        Comparator xComp = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.x - p2.x;
            }
        };
        Arrays.sort(xkeys, xComp);
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[0].length; j++) {
                int xpos = Arrays.binarySearch(xkeys, 0, xkeys.length, new Point(i, j), xComp);
                if (xpos < 0) {
                    xpos = -(xpos + 1);
                }
                if (xpos == xkeys.length) {
                    xpos--;
                }
                int ypos = Arrays.binarySearch(ykeys, 0, ykeys.length, new Point(i, j), yComp);
                if (ypos < 0) {
                    ypos = -(ypos + 1);
                }
                if (ypos == ykeys.length) {
                    ypos--;
                }
                int d1 = xkeys[xpos].dist(i, j);
                if (xpos+1 < xkeys.length) {
                    d1 = Math.min(d1, xkeys[xpos+1].dist(i, j));
                }
                if (xpos-1 >= 0) {
                    d1 = Math.min(d1, xkeys[xpos-1].dist(i, j));
                }
                int d2 = ykeys[ypos].dist(i, j);
                if (ypos+1 < ykeys.length) {
                    d2 = Math.min(d2, ykeys[ypos+1].dist(i, j));
                }
                if (ypos-1 >= 0) {
                    d2 = Math.min(d2, ykeys[ypos-1].dist(i, j));
                }
                ret[i][j] = Math.min(d1, d2);
            }
        }
        return ret;
    }

    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int dist(int x, int y) {
            return Math.abs(x-this.x) + Math.abs(y-this.y);
        }
    }
}

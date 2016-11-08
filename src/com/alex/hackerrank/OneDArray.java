package com.alex.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/11/7.
 */
public class OneDArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] values = new int[n];
            boolean[] path = new boolean[n];
            boolean reachable = false;
            for (int j = 0; j < n; j++) {
                int v = sc.nextInt();
                values[j] = v;
                if (reachable) {
                    continue;
                }
                if (j == 0 || (v == 0 && path[j-1]) || (v == 0 && j >= m && path[j-m])) {
                    path[j] = true;
                }
                if (path[j] && (n-j) <= m) {
                    reachable = true;
                    continue;
                }
                if (path[j]) {
                    for (int k = j - 1; k > 0 && !path[k] && values[k] == 0; k--) {
                        path[k] = true;
                    }
                }
            }
//            System.out.println(Arrays.toString(path));
            if (reachable || path[n-1]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }
}

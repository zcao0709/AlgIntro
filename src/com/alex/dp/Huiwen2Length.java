package com.alex.dp;

import java.util.List;

/**
 * Created by caozhennan on 2018/7/26.
 */
public class Huiwen2Length {
    public static int max(String str) {
        int max = 0;
        Huiwen maxByFar = new Huiwen(-1, -1, 0);
//        List<HuiwenLength>
//        for (int i = 0; i < str.length(); i++) {
//            maxByFar =
//        }
        return max;
    }

    private static class Huiwen {
        int left;
        int right;
        int len;

        public Huiwen(int left, int right, int len) {
            this.left = left;
            this.right = right;
            this.len = len;
        }
    }
}

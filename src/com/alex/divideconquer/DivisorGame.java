package com.alex.divideconquer;

import java.util.Arrays;

/**
 * Created by caozhennan on 2020/7/24 9:45 上午.
 */
public class DivisorGame {
    public boolean divisorGame(int N) {
        boolean[] b = new boolean[N+1];
        Arrays.fill(b, false);
        b[1] = false;
        b[2] = true;
        b[3] = false;
        for (int i = 4; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (j % i == 0 && !b[j]) {
                    b[i] = true;
                    break;
                }
            }
        }
        return b[N];
    }
}

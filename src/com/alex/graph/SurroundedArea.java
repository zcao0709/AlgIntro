package com.alex.graph;

import java.util.Arrays;

/**
 * Created by caozhennan on 2020/8/11 9:46 上午.
 */
public class SurroundedArea {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int[][] cache = new int[board.length][board[0].length];
        int i, j;
        for (i = 0, j = 0; j < board[i].length; j++) {
            if (board[i][j] == 'O') {
                mark(board, cache, i, j);
            }
        }
        for (i = board.length-1, j = 0; j < board[i].length; j++) {
            if (board[i][j] == 'O') {
                mark(board, cache, i, j);
            }
        }
        for (i = 0, j = 0; i < board.length; i++) {
            if (board[i][j] == 'O') {
                mark(board, cache, i, j);
            }
        }
        for (i = 0, j = board[0].length-1; i < board.length; i++) {
            if (board[i][j] == 'O') {
                mark(board, cache, i, j);
            }
        }
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                if (cache[i][j] == 0 && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    int[][] move = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };
    private void mark(char[][] board, int[][] cache, int i, int j) {
        if (i >= 0 && i < cache.length && j >= 0 && j < cache[i].length && board[i][j] == 'O') {
            if (cache[i][j] == 1) {
                return;
            }
            cache[i][j] = 1;

            for (int[] m : move) {
                mark(board, cache, i + m[0], j + m[1]);
            }
        }
    }

    public static void main(String[] args) {
//        char[][] board = new char[][] {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'},
//        };
        char[][] board = new char[][] {
                {'O','O','O','O','X','X'},
                {'O','O','O','O','O','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','X','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','O','O'},

        };
        new SurroundedArea().solve(board);
        for (char[] b : board) {
            System.out.println(Arrays.toString(b));
        }
    }
}

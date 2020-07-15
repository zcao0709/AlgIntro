package com.alex.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by caozhennan on 2019/12/2 6:59 下午.
 * https://www.hackerrank.com/challenges/magic-square-forming/problem
 * We define a magic square to be an matrix of distinct positive integers from to where the sum of any row, column, or diagonal of length
 *
 * is always equal to the same number: the magic constant.
 *
 * You will be given a
 * matrix of integers in the inclusive range . We can convert any digit to any other digit in the range at cost of . Given
 *
 * , convert it into a magic square at minimal cost. Print this cost on a new line.
 *
 * Note: The resulting magic square must contain distinct integers in the inclusive range
 *
 * .
 *
 * For example, we start with the following matrix
 *
 * :
 *
 * 5 3 4
 * 1 5 8
 * 6 4 2
 *
 * We can convert it to the following magic square:
 *
 * 8 3 4
 * 1 5 9
 * 6 7 2
 *
 * This took three replacements at a cost of
 *
 * .
 *
 * Function Description
 *
 * Complete the formingMagicSquare function in the editor below. It should return an integer that represents the minimal total cost of converting the input square to a magic square.
 *
 * formingMagicSquare has the following parameter(s):
 *
 *     s: a
 *
 *     array of integers
 *
 * Input Format
 *
 * Each of the lines contains three space-separated integers of row
 *
 * .
 *
 * Constraints
 *
 * Output Format
 *
 * Print an integer denoting the minimum cost of turning matrix
 *
 * into a magic square.
 *
 * Sample Input 0
 *
 * 4 9 2
 * 3 5 7
 * 8 1 5
 *
 * Sample Output 0
 *
 * 1
 *
 * Explanation 0
 *
 * If we change the bottom right value,
 * , from to at a cost of ,
 *
 * becomes a magic square at the minimum possible cost.
 *
 * Sample Input 1
 *
 * 4 8 2
 * 4 5 7
 * 6 1 6
 *
 * Sample Output 1
 *
 * 4
 *
 * Explanation 1
 *
 * Using 0-based indexing, if we make
 *
 * -> at a cost of -> at a cost of -> at a cost of
 *
 *     ,
 *
 * then the total cost will be
 * .
 */
public class MagicSquare {
    private interface Move {
        void move(int[] p);
    }
    static int formingMagicSquare(int[][] s) {
        int[] values = {8, 3, 4, 9, 2, 7, 6, 1};
        int[][] points = {{0, 0}, {0, 2}, {2, 2}, {2, 0}};
        Move[] moves = {MagicSquare::next, MagicSquare::prev};

        int ret = Integer.MAX_VALUE;
        System.out.println("next...");
        for (int[] p : points) {
            for (Move m : moves) {
                int result = 0;
                for (int v : values) {
                    result += Math.abs(s[p[0]][p[1]] - v);
                    System.out.printf("%s -> ", Arrays.toString(p));
                    m.move(p);
                    System.out.println(Arrays.toString(p) + ": " + result);
                }
                System.out.println("one result: " + result);
                ret = Math.min(ret, result);
            }
        }
        ret += Math.abs(s[1][1] - 5);
        return ret;
    }

    private static void next(int[] now) {
        int i = now[0];
        int j = now[1];
        if (j > i || i == 0) {
            j++;
            if (j == 3) {
                j--;
                i++;
            }
            if (i == 3) {
                i--;
                j--;
            }
        } else {
            j--;
            if (j == -1) {
                j++;
                i--;
            }
            if (i == -1) {
                i++;
                j++;
            }
        }
        now[0] = i;
        now[1] = j;
    }

    private static void prev(int[] now) {
        int i = now[0];
        int j = now[1];
        if (j > i || j == 2) {
            i--;
            if (i == -1) {
                i++;
                j--;
            }
            if (j == -1) {
                i++;
                i++;
            }
        } else {
            i++;
            if (i == 3) {
                i--;
                j++;
            }
            if (j == 3) {
                j--;
                i--;
            }
        }
        now[0] = i;
        now[1] = j;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }
        System.out.println(formingMagicSquare(s));
    }
}

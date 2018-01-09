package com.alex.misc;

import java.util.*;

/**
 * Created by caozhennan on 2018/1/9.
 */
// http://blog.csdn.net/code_cola/article/details/20655895
public class FlipGame {

    private static class Layout {
        private int hash;
        private int round;

        public Layout(int hash, int round) {
            this.hash = hash;
            this.round = round;
        }

        public Layout flip(List<Integer> fps) {
            int hash = this.hash;
            for (int fp : fps) {
                hash = flip(hash, fp);
            }
            return new Layout(hash, round + 1);
        }

        private int flip(int hash, int index) {
            if (index < 0) {
                return hash;
            }
            int b = 1;
            for (int i = 0; i < index; i++) {
                b = b << 1;
            }
            return hash ^ b;
        }

        public int getHash() {
            return hash;
        }

        public int getRound() {
            return round;
        }
    }

    private static class Game {
        private Layout init;
        private final int x;
        private final int y;
        private final int succ0 = 0;
        private final int succ1;

        private final Set<Integer> accessed = new HashSet<>();

        public Game(int[][] matrix) {
            if (matrix == null || matrix[0] == null) {
                init = new Layout(0, 0);
                x = 0;
                y = 0;
                succ1 = 1;
            } else {
                x = matrix.length;
                y = matrix[0].length;
                StringBuilder h = new StringBuilder(x * y);
                int s = 1;
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        h.append(matrix[i][j]);
                        s = s << 1;
                    }
                }
                init = new Layout(Integer.parseInt(h.toString(), 2), 0);
                succ1 = s - 1;
            }
        }

        public int play() {

            Queue<Layout> q = new LinkedList<>();
            q.offer(init);
            accessed.add(init.getHash());

            while (!q.isEmpty()) {
                Layout lo = q.poll();
                if (success(lo)) {
                    return lo.getRound();
                }
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        Layout l = flip(lo, i, j);
                        if (!accessed.contains(l.getHash())) {
                            q.offer(flip(lo, i, j));
                            accessed.add(l.getHash());
                        }
                    }
                }
            }
            return -1;
        }

        private boolean success(Layout lo) {
            return lo.getHash() == succ0 || lo.getHash() == succ1;
        }

        public Layout flip(Layout lo, int x, int y) {
            if (x < 0 || y < 0 || x >= this.x || y >= this.y) {
                throw new IllegalArgumentException("beyond the game board");
            }
            List<Integer> fps = new ArrayList<>(5);
            fps.add(xy2Index(x, y));
            fps.add(xy2Index(x-1, y));
            fps.add(xy2Index(x+1, y));
            fps.add(xy2Index(x, y-1));
            fps.add(xy2Index(x, y+1));
            return lo.flip(fps);
        }

        private int xy2Index(int x, int y) {
            if (x < 0 || y < 0 || x >= this.x || y >= this.y) {
                return -1;
            }
            return x * this.x + y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "], " + "[" + succ0 + "," + succ1 + "]";
        }
    }

    public static void main(String[] args) {
        int[][] m = new int[4][4];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = sc.nextInt();
            }
        }
        Game g = new Game(m);
        System.out.println(g);
        System.out.println(g.play());
    }
}

package com.alex.dp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by caozhennan on 2018/7/12.
 */
public class HuiwenLength {
    /* bug
    public static int max(String str) {
        int maxSofar = 0;
        int maxAtI = 1;
        for (int i = 1; i < str.length(); i++) {
            maxSofar = Math.max(maxSofar, maxAtI);
            maxAtI = huiwen(str, i, maxAtI);
            maxSofar = Math.max(maxSofar, maxAtI);
        }
        return maxSofar;
    }

    private static int huiwen(String str, int i, int max) {
        if (max == 1) {
            if (str.charAt(i) == str.charAt(i-1)) {
                System.out.println(str.substring(i-1, i+1));
                return 2;
            } else if (i > 1 && str.charAt(i) == str.charAt(i-2)) {
                System.out.println(str.substring(i-2, i+1));
                return 3;
            }
        } else {
            if (i > max && str.charAt(i) == str.charAt(i-max-1)) {
                System.out.println(str.substring(i-max-1, i+1));
                return max+2;
            }
        }
        System.out.println(str.substring(i, i+1));
        return 1;
    }
    */

    public static int max(String str) {
        int max = 1;

        List<Huiwen> sofar = new LinkedList<>();
        sofar.add(new Huiwen(0));

        for (int i = 1; i < str.length(); i++) {
            List<Huiwen> aux = new LinkedList<>();

            for (Huiwen ho : sofar) {
                Huiwen hn = ho.extend(str, i);
                if (hn != null) {
                    max = Math.max(hn.length(), max);
                    aux.add(hn);
                }
            }
            sofar = aux;
            sofar.add(new Huiwen(i));
            if (str.charAt(i) == str.charAt(i-1)) {
                sofar.add(new Huiwen(i-1, i));
                max = Math.max(max, 2);
            }
            System.out.printf("%d-%d: ", i, sofar.size());
            for (Huiwen h : sofar) {
                System.out.printf("%s ", str.substring(h.left, h.right+1));
            }
            System.out.println();
        }
        return max;
    }

    private static class Huiwen {
        final int left;
        final int right;

        public Huiwen(int left) {
            this.left = left;
            this.right = left;
        }

        public Huiwen(int left, int right) {
            this.left = left;
            this.right = right;
        }

        private boolean extendable(int len) {
            return left > 0 && right < len-1;
        }

        public int length() {
            return right - left + 1;
        }

        public Huiwen extend(String str, int i) {
            if (!extendable(str.length()) || right+1 != i) {
                return null;
            }
            if (str.charAt(left-1) == str.charAt(i)) {
                return new Huiwen(left-1, i);
            }
            return null;
        }
    }

    public static void main(String[] args) {
//        String str = "aaaaaa";
        String str = "cabaaba";
//        String str = "cababa";
//        String str = "abdd";
//        System.out.println(max(str));
        System.out.println(getMaxHuiWenLen(str));
    }

    private static int getMaxHuiWenLen(String s) {
        if(s == null) {
            return 0;
        }
        if(s.length() == 1) {
            return s.length();
        }
        int maxLen = 1;
        char[] arr = s.toCharArray();
        for(int i = 0; i < s.length(); i ++) {
            int tmpLen = 0;
            if(i+1 < s.length() && arr[i] == arr[i+1]) {
                for(int j = i, k = i +1; j >= 0 && k < s.length(); j--, k++) {
                    if(arr[j] == arr[k]) {
                        tmpLen += 2;
                    } else {
                        break;
                    }
                }
            }
            maxLen = Math.max(maxLen, tmpLen);
        }

        return maxLen;
    }
}

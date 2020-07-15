package com.alex.misc;

import java.util.Scanner;

public class Palindrome {
    // self revise the wechat' version, NOT a good version, abandoned
    public static String longestPalindrome(String str) {
        str = pre(str);
        System.out.println("pre: " + str);
        int[] radix = new int[str.length()];
        int rightmostR = 0;
        int rightmostC = 0;
        int maxR = 0;
        int maxC = 0;

        for (int i = 0; i < str.length(); i++) {
//            if (i == 0 || i == str.length() - 1) {
//                continue;
//            }
            int r = 0;
            if (i >= rightmostC + rightmostR) {
                r = expand(str, i, 0);
            } else {
                int mirror = rightmostC - (i - rightmostC);
                if (radix[mirror] + i >= rightmostC + rightmostR) {
                    System.out.println("expanding: " + i + ", " + radix[mirror] + ", " + mirror);
                    r = expand(str, i, rightmostC + rightmostR - i);
                } else {
                    r = radix[mirror];
                }
            }
            radix[i] = r;
            if (i + r > rightmostC + rightmostR) {
                rightmostC = i;
                rightmostR = r;
            }
            if (str.charAt(i + r) == '#') {
                r--;
            }
            if (r > maxR) {
                maxC = i;
                maxR = r;
            }
        }
        return post(str.substring(maxC - maxR, maxC + maxR + 1));
    }

    private static int expand(String s, int center, int radix) {
        while (true) {
            int left = center - radix - 1;
            int right = center + radix + 1;
            if (left < 0 || right >= s.length() || s.charAt(left) != s.charAt(right)) {
                break;
            }
            radix++;
        }
        System.out.println("expanded: " + center + ", " + radix);
        return radix;
    }

    private static String pre(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 2);
        sb.append("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append('#');
        }
        return sb.toString();
    }

    private static String post(String s) {
        System.out.println("post: " + s);
        StringBuilder sb = new StringBuilder(s.length() / 2);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#') {
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] cases = {
                "abcdcef",
                "adaelele",
                "cabadabae",
                "aaaabcdefgfedcbaa",
                "aaba",
                "aaaaaaaaa",
        };
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
        for (String str : cases) {
//            System.out.println("result: " + longestPalindrome(str));
            System.out.println("result: " + findLongestPalindromeString(str));
            System.out.println("result: " + maxPalindrome(str));
        }
    }

    // from wechat
    // 预处理字符串，在两个字符之间加上#
    private static String preHandleString(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for(int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }

    // 寻找最长回文字串
    public static String findLongestPalindromeString(String s) {
        // 先预处理字符串
        String str = preHandleString(s);
        // 处理后的字串长度
        int len = str.length();
        // 右边界
        int rightSide = 0;
        // 右边界对应的回文串中心
        int rightSideCenter = 0;
        // 保存以每个字符为中心的回文长度一半（向下取整）
        int[] halfLenArr = new int[len];
        // 记录回文中心
        int center = 0;
        // 记录最长回文长度
        int longestHalf = 0;
        for(int i = 0; i < len; i++) {
            // 是否需要中心扩展
            boolean needCalc = true;
            // 如果在右边界的覆盖之内
            if(rightSide > i) {
                // 计算相对rightSideCenter的对称位置
                int leftCenter = 2 * rightSideCenter - i;
                // 根据回文性质得到的结论
                halfLenArr[i] = halfLenArr[leftCenter];
                // 如果超过了右边界，进行调整
                if(i + halfLenArr[i] > rightSide) {
                    halfLenArr[i] = rightSide - i;
                }
                // 如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展了
                if(i + halfLenArr[leftCenter] < rightSide) {
                    // 直接推出结论
                    needCalc = false;
                }
            }
            // 中心扩展
            if(needCalc) {
                while(i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
                    if(str.charAt(i + 1 + halfLenArr[i]) == str.charAt(i - 1 - halfLenArr[i])) {
                        halfLenArr[i]++;
                    } else {
                        break;
                    }
                }
                // 更新右边界及中心
                rightSide = i + halfLenArr[i];
                rightSideCenter = i;
                // 记录最长回文串
                if(halfLenArr[i] > longestHalf) {
                    center = i;
                    longestHalf = halfLenArr[i];
                }
            }
        }
        // 去掉之前添加的#
        StringBuilder sb = new StringBuilder();
        for(int i = center - longestHalf + 1; i <= center + longestHalf; i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static String maxPalindrome(String str) {
        StringBuilder sb = new StringBuilder("#");
        for (char c : str.toCharArray()) {
            sb.append(c).append("#");
        }
        str = sb.toString();
        int maxCenter = 0;
        int maxRadius = 0;
        int rightmostCenter = 0;
        int rightmostRadius = 0;
        int[] radius = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            int rightmost = rightmostCenter + rightmostRadius;
            boolean needSwell = true;

            if (i < rightmost) {
                int mirror = 2 * rightmostCenter - i;
                if (i + radius[mirror] < rightmost) {
                    radius[i] = radius[mirror];
                    needSwell = false;
                } else {
                    radius[i] = rightmost - i;
                }
            }
            if (needSwell) {
                radius[i] = swell(str, i, radius[i]);
                if (i + radius[i] > rightmost) {
                    rightmostCenter = i;
                    rightmostRadius = radius[i];
                }
            }
            if (radius[i] > maxRadius) {
                maxRadius = radius[i];
                maxCenter = i;
            }
        }
        sb.setLength(0);
        for (int i = maxCenter - maxRadius + 1; i < maxCenter + maxRadius; i++) {
            if (str.charAt(i) != '#') {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    private static int swell(String str, int center, int radius) {
        int left = center - radius - 1;
        int right = center + radius + 1;
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            radius++;
            left--;
            right++;
        }
        return radius;
    }
}











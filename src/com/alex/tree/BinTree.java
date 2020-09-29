package com.alex.tree;

import com.alex.common.BinaryTree;
import com.alex.common.TNode;

import java.util.*;

/**
 * Created by caozhennan on 2017/11/21.
 */
public class BinTree {

    public static void printByLevel(TNode head) {
        if (head == null) {
            return;
        }
        Deque<TNode> q = new LinkedList<>();
        q.offer(head);
        TNode last = head;
        int level = 1;
        System.out.print(level + ": ");

        while (!q.isEmpty()) {
            TNode n = q.poll();
            System.out.print(n.value + " ");
            if (n.left != null) {
                q.offer(n.left);
            }
            if (n.right != null) {
                q.offer(n.right);
            }
            if (n == last) {
                System.out.println();
                if (!q.isEmpty()) {
                    level++;
                    System.out.print(level + ": ");
                    last = q.peekLast();
                }
            }
        }
    }

    public static void printZigzag(TNode head) {
        if (head == null) {
            return;
        }
        Deque<TNode> q = new LinkedList<>();
        q.offer(head);
        boolean lr = true;
        TNode last = head;
//        TNode nextLast = null;
        int level = 1;
        System.out.print(level + "->: ");
        TNode n = null;

        while (!q.isEmpty()) {
            if (lr) {
                n = q.pollFirst();
                if (n.left != null) {
                    q.offerLast(n.left);
                }
                if (n.right != null) {
                    q.offerLast(n.right);
                }
            } else {
                n = q.pollLast();
                if (n.right != null) {
                    q.offerFirst(n.right);
                }
                if (n.left != null) {
                    q.offerFirst(n.left);
                }
            }
            System.out.print(n.value + " ");
            if (n == last) {
                System.out.println();
                if (!q.isEmpty()) {
                    level++;
                    System.out.print(level);
                    lr = !lr;
                    if (lr) {
                        System.out.print("->: ");
                        last = q.peekLast();
                    } else {
                        System.out.print("<-: ");
                        last = q.peekFirst();
                    }
                }
            }
        }
    }

    public static void printPreOrder(TNode head) {
        if (head == null) {
            return;
        }
        Stack<TNode> s = new Stack<>();
        s.push(head);
        while (!s.isEmpty()) {
            TNode n = s.pop();
            System.out.print(n.value + " ");
            if (n.right != null) {
                s.push(n.right);
            }
            if (n.left != null) {
                s.push(n.left);
            }
        }
        System.out.println();
    }

    public static void printInOrder(TNode head) {
        if (head == null) {
            return;
        }
        Stack<TNode> s = new Stack<>();
        while (!s.isEmpty() || head != null) {
            if (head != null) {
                s.push(head);
                head = head.left;
            } else {
                head = s.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    public static void printPostOrder(TNode head) {
        if (head == null) {
            return;
        }
        Stack<TNode> s1 = new Stack<>();
        Stack<TNode> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            TNode n = s1.pop();
            s2.push(n);
            if (n.left != null) {
                s1.push(n.left);
            }
            if (n.right != null) {
                s1.push(n.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
        System.out.println();
    }

    public static int maxRoute(TNode head) {
        if (head == null) {
            return 0;
        }
        int[] maxHalf = new int[1];
        return maxRoute(head, maxHalf);
    }
    // return the longest route in tree rooted by node
    // maxHalf saves the longest route from some leaf to node
    private static int maxRoute(TNode node, int[] maxHalf) {
        if (node == null) {
            maxHalf[0] = 0;
            return 0;
        }
        int lmax = maxRoute(node.left, maxHalf);
        int lhalf = maxHalf[0];
        int rmax = maxRoute(node.right, maxHalf);
        int rhalf = maxHalf[0];
        int selfMax = lhalf + rhalf + 1;
        maxHalf[0] = Math.max(lhalf, rhalf) + 1;
        return Math.max(selfMax, Math.max(lmax, rmax));
    }

    public static boolean isBST(TNode head) {
        if (head == null) {
            return true;
        }
        return isBST(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private static boolean isBST(TNode node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.value < min || node.value > max) {
            return false;
        }
        if (isBST(node.left, min, node.value-1) && isBST(node.right, node.value+1, max)) {
            return true;
        }
        return false;
    }

    public static boolean isValidBST(TNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if ((min != null && node.value <= min) || (max != null && node.value >= max)) {
            return false;
        }
        return isValidBST(node.left, min, node.value) &&
                isValidBST(node.right, node.value, max);
    }

    public static int findLongestRouteWithSum(TNode head, int sum) {
        if (head == null) {
            return 0;
        }
        Map<Integer, Integer> historySum = new HashMap<>();
        historySum.put(0, 0);
        return findLongestRouteWithSum(head, sum, 0, 0, 1, historySum);
    }
    private static int findLongestRouteWithSum(TNode head, int sum, int lastSum, int longest, int level, Map<Integer, Integer> historySum) {
        if (head == null) {
            return longest;
        }
        int curSum = lastSum + head.value;
        if (historySum.containsKey(curSum - sum)) {
            longest = Math.max(longest, level - historySum.get(curSum - sum));
        }
        if (!historySum.containsKey(curSum)) {
            historySum.put(curSum, level);
        }
        longest = findLongestRouteWithSum(head.left, sum, curSum, longest, level + 1, historySum);
        longest = findLongestRouteWithSum(head.right, sum, curSum, longest, level + 1, historySum);
        if (level == historySum.get(curSum)) {
            historySum.remove(curSum);
        }
        return longest;
    }

    public static TNode biggestBST(TNode head) {
        if (head == null) {
            return null;
        }
        // num, min, max
        int[] stat = new int[3];
        TNode node = biggestBST(head, stat);
        System.out.println(Arrays.toString(stat));
        return node;
    }
    public static TNode biggestBST(TNode node, int[] stat) {
        if (node == null) {
            stat[0] = 0;
            stat[1] = Integer.MAX_VALUE;
            stat[2] = Integer.MIN_VALUE;
            return null;
        }
        TNode lbst = biggestBST(node.left, stat);
        int lsize = stat[0];
        int lmin = stat[1];
        int lmax = stat[2];

        TNode rbst = biggestBST(node.right, stat);
        int rsize = stat[0];
        int rmin = stat[1];
        int rmax = stat[2];

        stat[1] = Math.min(node.value, lmin);
        stat[2] = Math.max(node.value, rmax);
        if (lbst == node.left && rbst == node.right && node.value > lmax && node.value < rmin) {
            stat[0] = lsize + rsize + 1;
            System.out.println(node.toString() + ", " + Arrays.toString(stat));
            return node;
        }
        if (lsize > rsize) {
            stat[0] = lsize;
            System.out.println(lbst.toString() + ", " + Arrays.toString(stat));
            return lbst;
        } else {
            stat[0] = rsize;
            System.out.println(rbst.toString() + ", " + Arrays.toString(stat));
            return rbst;
        }
    }

    public static boolean isSameTree(TNode p, TNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.value == q.value) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    public static boolean isSubStructure(TNode a, TNode b) {
        if (a == null || b == null) {
            return false;
        }
        return isSubStructureR(a, b) || isSubStructure(a.left, b) || isSubStructure(a.right, b);
    }

    private static boolean isSubStructureR(TNode a, TNode b) {
        if (b == null) {
            return true;
        } else if (a == null || a.value != b.value) {
            return false;
        } else {
            return isSubStructureR(a.left, b.left) && isSubStructureR(a.right, b.right);
        }
    }

    private static void tree2String(TNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.value).append(",");
        tree2String(node.left, sb);
        tree2String(node.right, sb);
    }

    public static void BinTree2List(TNode root) {
        if (root == null) {
            return;
        }
        BinTree2List(root.right);
        BinTree2List(root.left);
        if (root.left != null) {
            TNode r = root.right;
            TNode n = root.left;
            root.right = root.left;
            while (n.right != null) {
                n = n.right;
            }
            n.right = r;
            root.left = null;
        }
    }

    public static void main(String[] args) {
//        BinaryTree tree = BinaryTree.valueOf(17);
//        TNode head = tree.root;
//        printByLevel(head);
//        printZigzag(head);
//        printPreOrder(head);
//        printInOrder(head);
//        printPostOrder(head);
//        System.out.println(biggestBST(head));
        TNode a = new TNode(10);
        a.left = new TNode(12);
        a.right = new TNode(6);
        a.left.left = new TNode(8);
        a.left.right = new TNode(3);
        a.right.left = new TNode(11);

        TNode b = new TNode(10);
        b.left = new TNode(12);
        b.right = new TNode(6);
        b.left.left = new TNode(8);
        isSubStructure(a, b);
    }
}

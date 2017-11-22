package com.alex.misc;

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

    public static void main(String[] args) {
        TNode head = TNode.create(10);
        printByLevel(head);
        printZigzag(head);
        printPreOrder(head);
        printInOrder(head);
        printPostOrder(head);
    }
}

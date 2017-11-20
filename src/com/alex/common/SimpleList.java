package com.alex.common;

/**
 * Created by caozhennan on 2017/11/20.
 */
public class SimpleList {

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
            next = null;
        }
    }
}
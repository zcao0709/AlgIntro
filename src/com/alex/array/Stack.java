package com.alex.array;

/**
 * Created by caozhennan on 2018/7/9.
 */
public class Stack {
    private int[] arr;
    private int index;

    public Stack(int size) {
        arr = new int[size];
        index = 0;
    }

    public void push(int val) {
        if (index == arr.length) {
            throw new IllegalArgumentException("stack overflow");
        }
        arr[index++] = val;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("stack underflow");
        }
        int val = arr[--index];
        return val;
    }

    public boolean isEmpty() {
        return index == 0;
    }
}

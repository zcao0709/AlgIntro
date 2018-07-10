package com.alex.array;

/**
 * Created by caozhennan on 2018/7/9.
 */
public class QueueBy2Stacks {
    private Stack s1;
    private Stack s2;

    public QueueBy2Stacks(int size) {
        s1 = new Stack(size);
        s2 = new Stack(size);
    }

    public void offer(int val) {
        s1.push(val);
    }

    public int poll() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int val = s2.pop();
        return val;
    }

    public static void main(String[] args) {
        QueueBy2Stacks q = new QueueBy2Stacks(10);
        q.offer(1);
        q.offer(2);
        q.offer(3);
        System.out.println(q.poll());
        System.out.println(q.poll());
        q.offer(4);
        q.offer(5);
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
}

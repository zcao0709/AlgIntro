package com.alex.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by caozhennan on 2018/6/27.
 */
public class CLHLock implements Lock {
    private AtomicReference<QNode> tail;
    ThreadLocal<QNode> me;
    ThreadLocal<QNode> pre;

    public CLHLock() {
        tail = new AtomicReference<>(new QNode(Thread.currentThread().getName()));
        me = ThreadLocal.withInitial(() -> new QNode(Thread.currentThread().getName()));
        pre = ThreadLocal.withInitial(() -> null);
//        System.out.println(Thread.currentThread().getName() + "c/me: " + me.get());
//        System.out.println(Thread.currentThread().getName() + "c/pre: " + pre.get());
    }

    @Override
    public void lock() {
        QNode n = me.get();
        n.lock = true;
        System.out.println(Thread.currentThread().getName() + "lock/me: " + n);
        QNode pn = tail.getAndSet(n);
        pre.set(pn);
        System.out.println(Thread.currentThread().getName() +"lock/pre: " + pn);

        while (pn.lock) {
        }
    }

    @Override
    public void unlock() {
        QNode n = me.get();
        n.lock = false;
        System.out.println(Thread.currentThread().getName()+ "unlock/me: " + n);
        QNode pn = pre.get();
        System.out.println(Thread.currentThread().getName() + "unlock/me and pre: " + pn);
        me.set(pn);
    }

    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException("no tryLock");
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        throw new UnsupportedOperationException("no tryLock");
    }

    @Override
    public void lockInterruptibly() {
        throw new UnsupportedOperationException("no lockInterruptibly");
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("no newCondition");
    }

    private static class QNode {
        String name;
        volatile boolean lock;

        public QNode(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return name + lock;
        }
    }

    public static void main(String[] args) {
        int[] i = new int[1];
        i[0] = 0;
        CLHLock lock = new CLHLock();
        CountDownLatch latch = new CountDownLatch(1);

        class Task implements Runnable {
            @Override
            public void run() {
                try {
                    latch.await();
//                    System.out.println(Thread.currentThread().getName() + " waiting");
//                    System.out.flush();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int k = 0; k < 3; k++) {
                    try {
                        lock.lock();
                        int j = i[0];
                        j++;
                        try {
                            Thread.sleep(new Random().nextInt(5));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i[0] = j;
//                        System.out.println(Thread.currentThread().getName() + " done");
//                        System.out.flush();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        Thread[] ts = new Thread[3];
        for (int j = 0; j < ts.length; j++) {
            ts[j] = new Thread(new Task());
        }
        for (Thread t : ts) {
            t.start();
        }
        latch.countDown();
        System.out.println(Thread.currentThread().getName() + " release");
        System.out.flush();
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("i = " + i[0]);
    }
}

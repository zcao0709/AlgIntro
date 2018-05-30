package com.alex;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by caozhennan on 2018/2/14.
 */
public class ThreadLocalTest {
    private static volatile Set<String> s;
    static {
        s = new HashSet<>();
        s.add("test1");
    }

    private static class Fork extends Thread {
        ThreadLocal<Set<String>> tl;

        public Fork(String name) {
            super(name);
        }

        @Override
        public void run() {
            tl = new ThreadLocal<>();
            tl.set(s);
            System.out.println(getName() + tl.get().size());
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + tl.get().size());
        }
    }

    public static void main(String[] args) {
        Fork f1 = new Fork("f1");
        f1.start();

        Fork f2 = new Fork("f2");
        f2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReadWriteLock rw = new ReentrantReadWriteLock();
        rw.readLock().lock();
        rw.writeLock().lock();
        System.out.println("changing");
//        s.add("test_again");
        s = new HashSet<>();
        System.out.println("changed");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

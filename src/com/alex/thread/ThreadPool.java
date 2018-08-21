package com.alex.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by caozhennan on 2018/7/21.
 */
public class ThreadPool {
    private BlockingQueue<Runnable> tasks;

    public ThreadPool(int size) {
        this.tasks = new LinkedBlockingDeque<>(size);
    }
}

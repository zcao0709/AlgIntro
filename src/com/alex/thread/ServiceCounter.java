package com.alex.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by caozhennan on 2018/6/30.
 */
public class ServiceCounter {
    private ConcurrentHashMap<String, AtomicLong> counter;
    private static AtomicLong zero = new AtomicLong(0L);

    public ServiceCounter() {
        counter = new ConcurrentHashMap<>();
    }

    public long update(String sid) {
        counter.putIfAbsent(sid, new AtomicLong(0L));

        AtomicLong ac = counter.get(sid);
        return ac.incrementAndGet();
    }

    public long get(String sid) {
        return counter.getOrDefault(sid, zero).get();
    }
}

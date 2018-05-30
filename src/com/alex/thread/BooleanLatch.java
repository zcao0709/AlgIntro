package com.alex.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by caozhennan on 2018/5/30.
 */
// Here is a latch class that is like a CountDownLatch except that it only
// requires a single signal to fire. Because a latch is non-exclusive,
// it uses the shared acquire and release methods.
public class BooleanLatch {
    private static class Sync extends AbstractQueuedSynchronizer {
        boolean isSignalled() { return getState() != 0; }

        protected int tryAcquireShared(int ignore) {
            return isSignalled() ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignore) {
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();
    public boolean isSignalled() { return sync.isSignalled(); }
    public void signal()         { sync.releaseShared(1); }
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
}

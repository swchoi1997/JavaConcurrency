package org.example.sync.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private int count = 0;

    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count += 1;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        LockExample lockExample = new LockExample();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                lockExample.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                lockExample.increment();
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println(lockExample.getCount());

    }
}

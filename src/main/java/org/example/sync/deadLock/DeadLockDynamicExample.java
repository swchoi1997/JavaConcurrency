package org.example.sync.deadLock;

public class DeadLockDynamicExample {

    private static final Object lock = new Object();
    private static final Object lock2 = new Object();
    public static void main(String[] args) {
        new Thread(() -> DeadLockDynamicExample.process(lock, lock2)).start();
        new Thread(() -> DeadLockDynamicExample.process(lock2, lock)).start();

    }

    private static void process(Object lockA, Object lockB) {
        synchronized (lockA) {
            System.out.println("Thread acquired lock1");
            synchronized (lockB) {
                System.out.println("Thread acquired lock2");
            }
        }
    }

}

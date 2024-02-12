package org.example.sync.deadLock;

public class NonDeadLockDynamicOrderExample {

    private static final Object lock = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> methodWithLock(lock, lock2)).start();
        new Thread(() -> methodWithLock(lock2, lock)).start();
    }
    private static void methodWithLock(Object lockA, Object lockB) {
        Object firstLock = lockA;
        Object secondLock = lockB;

        if (System.identityHashCode(lockA) > System.identityHashCode(lockB)) {
            firstLock = lockB;
            secondLock = lockA;
        }

        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + "  : lock A acquired");

            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + "  : lock B acquired");
            }
        }
    }
}

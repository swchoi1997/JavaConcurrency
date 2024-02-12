package org.example.sync.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockExample {

    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true));
    }

    public void unlock() {
        lock.set(false);
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockExample spinlock = new SpinLockExample();
        Runnable task = () -> {
            spinlock.lock();
            System.out.println(Thread.currentThread().getName() + "  Get Lock");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + "  UnLock");
                spinlock.unlock();
            }
        };

        Thread thread = new Thread(task);
        Thread thread2 = new Thread(task);

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

    }
}

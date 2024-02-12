package org.example.sync.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {
                    System.out.println("Thread11111 get lock");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        System.out.println("Thread11111  unlock");
                        lock.unlock();
                    }

                } else {
                    System.out.println("Thread11111 does not get lock");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {
                    System.out.println("Thread22222 get lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        System.out.println("Thread22222  unlock");
                        lock.unlock();
                    }

                } else {
                    System.out.println("Thread22222 does not get lock");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}

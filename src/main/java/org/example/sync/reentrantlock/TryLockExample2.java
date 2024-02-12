package org.example.sync.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample2 {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {

            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("Thread11111 get lock");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        System.out.println("Thread11111  unlock");
                        lock.unlock();
                    }

                } else {
                    System.out.println("Thread11111 does not get lock");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        Thread thread2 = new Thread(() -> {

            try {
                if (lock.tryLock(4, TimeUnit.SECONDS)) {
                    System.out.println("Thread22222 get lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        System.out.println("Thread22222  unlock");
                        lock.unlock();
                    }

                } else {
                    System.out.println("Thread22222 does not get lock");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });


        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();


    }
}

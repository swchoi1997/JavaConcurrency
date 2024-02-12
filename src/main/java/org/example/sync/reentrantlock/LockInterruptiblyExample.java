package org.example.sync.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {

            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("Thread11111 get lock");
                }finally {
                    lock.unlock();
                    System.out.println("Thread11111  unlock");
                }

            } catch (InterruptedException e) {
                System.out.println("Thread1111111 Interupted");
            }
        });

        Thread thread2 = new Thread(() -> {

            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("Thread22222 get lock");
                }finally {
                    lock.unlock();
                    System.out.println("Thread22222  unlock");
                }

            } catch (InterruptedException e) {
                System.out.println("Thread222222 Interupted");
            }
        });

        thread1.start();
        thread2.start();

        thread1.interrupt();

        thread1.join();
        thread2.join();

    }
}

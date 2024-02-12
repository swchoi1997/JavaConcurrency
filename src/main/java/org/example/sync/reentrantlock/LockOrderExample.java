package org.example.sync.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockOrderExample {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock1.lock();
            try{
                System.out.println("Thread Get Lock1");
                lock2.lock();
                try{
                    System.out.println("Thread Get Lock2");
                }finally {
                    System.out.println("Thread Unlock Lock1");
                    lock1.unlock();
                }
            }finally {
                lock2.unlock();
                System.out.println("Thread Unlock Lock2");
            }
        }).start();
    }
}

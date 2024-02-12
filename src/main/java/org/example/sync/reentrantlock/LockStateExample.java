package org.example.sync.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {

    private static final Lock lock1 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock1.lock();
            try{
                System.out.println("Thread Get Lock Count -> 1");
                lock1.lock();
                try{
                    System.out.println("Thread Get Lock Count -> 2");
                    lock1.lock();
                    try{
                        System.out.println("Thread Get Lock Count -> 3");
                    }finally {
                        lock1.unlock();
                        System.out.println("Thread Unlock Count -> 2");
                    }
                }finally {
                    lock1.unlock();
                    System.out.println("Thread Unlock Count -> 1");
                }
            }finally {
//                lock1.unlock();
                System.out.println("Thread Unlock Count -> 0");
            }
        }).start();


        new Thread(() -> {
            lock1.lock();
            try{
                System.out.println("Thread 2 Get Lock");
            } finally {
                lock1.unlock();
                System.out.println("Thread 2 Unlock Lock");
            }
        }).start();
    }

}

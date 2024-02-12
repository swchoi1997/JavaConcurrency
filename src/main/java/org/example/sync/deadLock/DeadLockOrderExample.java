package org.example.sync.deadLock;

public class DeadLockOrderExample {
    private static final Object lock = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(DeadLockOrderExample::process1).start();
        new Thread(DeadLockOrderExample::process2).start();
    }

    private static void process1() {
        synchronized (lock) {
            System.out.println("Thread 1 acquired lock1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
                System.out.println("Thread 1 acquired lock2");
            }
        }
    }

    private static void process2() {
        synchronized (lock2) {
            System.out.println("Thread 1 acquired lock2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock) {
                System.out.println("Thread 1 acquired lock1");
            }
        }
    }

}

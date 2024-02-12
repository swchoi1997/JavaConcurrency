package org.example.sync.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
    private static final AtomicBoolean flag = new AtomicBoolean(false);
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("Thread Busy Wait");
                }
                System.out.println("Thread 1 Critical Section Run...");
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("Thread 2 Busy Wait");
                }
                System.out.println("Thread 2 Critical Section Run...");
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

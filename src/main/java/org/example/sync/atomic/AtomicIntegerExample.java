package org.example.sync.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class AtomicIntegerExample {
    private static AtomicInteger value = new AtomicInteger(0);


    private static int NUM_THREAD = 5;

    public static void main(String[] args) throws InterruptedException {
        Thread[] thread = new Thread[NUM_THREAD];

        for (int i = 0; i < NUM_THREAD; i++) {
            thread[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    value.incrementAndGet();
                    IntUnaryOperator add = value -> value + 10 - 8;
                    value.updateAndGet(add);
                }
            });
            thread[i].start();
        }

        for (Thread thread1 : thread) {
            thread1.join();
        }

        System.out.println("Final Value : " + value.get());
    }
}

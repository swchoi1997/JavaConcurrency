package org.example.sync.semaphore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) throws InterruptedException {
        SharedData sharedData = new SharedData(new BinarySemaphore());

        Thread thread1 = new Thread(sharedData::sum);
        Thread thread2 = new Thread(sharedData::sum);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(sharedData.get());


        Semaphore semaphore = new Semaphore(1);

        Thread.sleep(1000);

        SharedData sharedData1 = new SharedData(new CountingSemaphore(10));

        Collection<Thread> th = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            th.add(new Thread(() -> {
                synchronized (SemaphoreExample.class) {
                    sharedData1.sum();
                }
            }));
        }

        for (Thread thread : th) {
            thread.start();
        }

        for (Thread thread : th) {
            thread.join();
        }

        System.out.println("2222 > " + sharedData1.get());

    }
}

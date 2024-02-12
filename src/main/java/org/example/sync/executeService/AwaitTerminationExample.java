package org.example.sync.executeService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class AwaitTerminationExample {
    //Daemon Thread일때 참고해야할 사항이 있음
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        executorService.submit(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " -> Daemon Thread Running.....");
                Thread.sleep(1000);

            }
        });

        executorService.shutdownNow();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(" Main Thread Finished");

    }
}

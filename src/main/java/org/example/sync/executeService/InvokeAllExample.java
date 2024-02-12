package org.example.sync.executeService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = new ArrayList<>();

        tasks.add(() -> {
            Thread.sleep(3000);
            return "1";
        });

        tasks.add(() -> {
            Thread.sleep(2000);
            return "2";
        });

        tasks.add(() -> {
            throw new RuntimeException("InvokeAll Error");
        });

        long started = 0;
        try {
            started = System.currentTimeMillis();
            List<Future<String>> futures = executorService.invokeAll(tasks);
            for (Future<String> future : futures) {
                try {
                    String s = future.get();

                    System.out.println("s = " + s);
                } catch (ExecutionException e) {
                    System.out.println(e.getMessage());

                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Time : " + String.valueOf(System.currentTimeMillis() - started));

        executorService.shutdown();

    }
}

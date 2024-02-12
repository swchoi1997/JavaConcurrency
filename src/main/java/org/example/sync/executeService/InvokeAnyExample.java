package org.example.sync.executeService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAnyExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
            Thread.sleep(1000);
            return "3";
        });

        long started = 0;
        try {
            started = System.currentTimeMillis();
            String s1 = executorService.invokeAny(tasks);
            System.out.println("s1 = " + s1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Time : " + String.valueOf(System.currentTimeMillis() - started));

        executorService.shutdown();

    }
}

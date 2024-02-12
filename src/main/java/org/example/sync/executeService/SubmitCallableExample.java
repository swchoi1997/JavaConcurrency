package org.example.sync.executeService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitCallableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> submit = executorService.submit(() -> {
            System.out.println("Async Job Run");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Async Job Finish");

            return 42;
        });

        try {
            Integer i = submit.get();
            System.out.println(i);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


        executorService.shutdown();
    }
}

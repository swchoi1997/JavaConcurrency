package org.example.sync.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureGetExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("Async Job Start ");
            Thread.sleep(2000);

            System.out.println("Async Job end ");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        while (!future.isDone()) {
            System.out.println("Waiting....");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Integer i = future.get();
            System.out.println("Result -> " + i);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}

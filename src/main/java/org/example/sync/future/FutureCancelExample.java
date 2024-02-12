package org.example.sync.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("Async Job Start ");
            Thread.sleep(2000);

            System.out.println("Async Job end ");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        future.cancel(true);
//        future.cancel(false);

        try {
            Integer i = future.get();
            System.out.println("Result -> " + i);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Job Is Cancelled Not Interrupt");
            throw new RuntimeException(e);
        } catch (CancellationException cancellationException) {
            System.out.println("Job Is Cancelled With Interrupt");
        }

        executorService.shutdown();
    }
}

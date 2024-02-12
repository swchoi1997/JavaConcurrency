package org.example.sync.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer i = CompletableFuture.supplyAsync(() -> {
            System.out.println("Service 1 Start");

            return 1;
        }).thenApplyAsync(result1 -> {
            System.out.println("Service 2 Start");

            return result1 + 2;
        }).thenApplyAsync(result1 -> {
            System.out.println("Service 3 Start");

            return result1 + 2;
        }).thenApplyAsync(result1 -> {
            System.out.println("Service 4 Start");

            return result1 + 2;
        }).thenApplyAsync(result1 -> {
            System.out.println("Service 5 Start");

            return result1 + 2;
        }).get();

        System.out.println("i = " + i);


    }
}

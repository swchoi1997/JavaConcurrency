package org.example.sync.threadPool;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
            System.out.println("Callbale Job Starting...");
            Thread.sleep(1000);
            System.out.println("Callbale Job End");

            return 42;
        };

        Future<Integer> future = executorService.submit(callable);
        int count = 0;
        while(!future.isDone()){
            count++;
            Thread.sleep(10);
        }
        Integer i = future.get();
        System.out.println(count + " : Result -> " + i);

        executorService.shutdown();

    }
}

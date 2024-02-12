package org.example.sync.futureCallback;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallbackExample {

    interface Callback{
        void onComplete(int result);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
            System.out.println("Callbale Job Starting...");
            Thread.sleep(1000);
            System.out.println("Callbale Job End");

            return 42;
        };

        Future<Integer> future = executorService.submit(callable);

        System.out.println("비동기 작업 시작");

        registerCallback(future, result -> {
            System.out.println("비동기 작업 결과 -> " + result);
        });

        executorService.shutdown();


    }

    private static void registerCallback(final Future<Integer> future, final Callback callback) {
        new Thread(() -> {
            Integer i = null;
            try {
                i = future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            callback.onComplete(i);
        }).start();
    }
}

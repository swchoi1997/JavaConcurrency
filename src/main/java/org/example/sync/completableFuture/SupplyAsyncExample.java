package org.example.sync.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncExample {
    public static void main(String[] args) throws InterruptedException {

        MyService myService = new MyService();

        CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " Is Rnun Async Start");
            return myService.getData();
        });

        List<Integer> join = future.join();
        join.forEach(System.out::println);
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Thread.sleep(1000);
    }



}

class MyService{
    public List<Integer> getData() {
        return Arrays.asList(1, 2, 3);
    }
}

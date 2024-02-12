package org.example.sync.executeService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            System.out.println("Async Job Run");
        });

        executorService.shutdown();
    }
}

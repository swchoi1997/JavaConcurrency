package org.example.sync.threadPool;

import java.util.concurrent.Executor;

public class AsyncExecutorExample {

    public static void main(String[] args) {
        AsyncExecutor syncExecutor = new AsyncExecutor();
        syncExecutor.execute(() -> {
            for (int i = 0; i < 1000000; i++) {
                continue;
            }
            System.out.println("Sync Job Start");

        });

        System.out.println("Main");
    }

    static class AsyncExecutor implements Executor {

        @Override
        public void execute(final Runnable command) {
            new Thread(command).start();

        }
    }
}

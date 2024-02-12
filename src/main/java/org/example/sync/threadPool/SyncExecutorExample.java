package org.example.sync.threadPool;

import java.util.concurrent.Executor;

public class SyncExecutorExample {

    public static void main(String[] args) {
        SyncExecutor syncExecutor = new SyncExecutor();
        syncExecutor.execute(() -> {
            for (int i = 0; i < 1000000; i++) {
                continue;
            }
            System.out.println("Sync Job Start");

        });

        System.out.println("Main");
    }

    static class SyncExecutor implements Executor {

        @Override
        public void execute(final Runnable command) {
            command.run();
        }
    }
}

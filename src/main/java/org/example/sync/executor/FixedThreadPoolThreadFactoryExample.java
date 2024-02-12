package org.example.sync.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixedThreadPoolThreadFactoryExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5,new CustomThreadFactory("Custom"));

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread.-> " + Thread.currentThread().getName());
            });

        }
        executorService.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory {
        private final String name;
        private int threadCnt = 0;

        public CustomThreadFactory(final String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(final Runnable r) {
            this.threadCnt++;
            String threadName = this.name + this.threadCnt;
            return new Thread(r, threadName);

        }
    }
}

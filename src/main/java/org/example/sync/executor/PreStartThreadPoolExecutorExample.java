package org.example.sync.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PreStartThreadPoolExecutorExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10L;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
//        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        int tasknum = 9;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);

        executor.prestartCoreThread();

        for (int i = 0; i < tasknum; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "  Is Task " + taskId + " Running.....");
            });
        }

        executor.shutdown();
    }
}

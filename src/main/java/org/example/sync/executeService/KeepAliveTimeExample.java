package org.example.sync.executeService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KeepAliveTimeExample {
    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        int keepAliveTime = 1;
        BlockingDeque<Runnable> objects = new LinkedBlockingDeque<>(8);
//        BlockingQueue<Runnable> objects = new ArrayBlockingQueue<>(4);

        int taskNum = 8;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                objects);

        for (int i = 0; i < taskNum; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " is Task" + taskNumber + " Running");
            });
        }

        Thread.sleep(4000);
        executor.shutdown();
    }
}

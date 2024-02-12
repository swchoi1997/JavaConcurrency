package org.example.sync.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task2 = () -> {
            System.out.println("Task222 is Running");
        };
        Runnable task = () -> {
            System.out.println("Task111 is Running");
        };

        int initialDelay = 0; // ㅊㅗ기 지연
        int initialPeriod = 1; // 초기주기
        int initialPeriod2 = 2; // 초기주기
        int updatePeriod = 3; // 변겨된 주기
        int updatePeriod2 = 4; // 변겨된 주기

        ScheduledFuture<?> future1 = scheduledExecutorService.scheduleAtFixedRate(task, initialDelay,
                initialPeriod, TimeUnit.SECONDS);
        ScheduledFuture<?> future2 = scheduledExecutorService.scheduleWithFixedDelay(task2, initialDelay,
                initialPeriod2, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000);
            future1.cancel(true);

            future1 = scheduledExecutorService.scheduleAtFixedRate(task, initialDelay,
                    updatePeriod, TimeUnit.SECONDS);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        future1.cancel(false);
        future2.cancel(false);

        scheduledExecutorService.shutdown();
    }
}

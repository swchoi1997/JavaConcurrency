package org.example.sync.executeService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        Runnable task = () -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " Job Run");
        };

        ScheduledFuture<?> schedule = scheduler.scheduleAtFixedRate(task, 1, 4, TimeUnit.SECONDS);
        ScheduledFuture<?> schedule2 = scheduler.scheduleAtFixedRate(task, 1, 4, TimeUnit.SECONDS);

        int cnt = 0;
        while (!schedule.isDone()) {
            long delay = schedule.getDelay(TimeUnit.MILLISECONDS);
            long delay2 = schedule2.getDelay(TimeUnit.MILLISECONDS);
            System.out.println("delay = " + delay);
            System.out.println("delay2 = " + delay2);
            if (cnt == 10) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cnt++;
        }

        schedule.cancel(false);
        schedule2.cancel(false);

        if (schedule.isDone() && !schedule.isCancelled()) {
            System.out.println("schedule Done");
        } else{
            System.out.println("schedule Cancelled");
        }

        if (schedule2.isDone() && !schedule2.isCancelled()) {
            System.out.println("schedule2 Done");
        } else{
            System.out.println("schedule2 Cancelled");
        }


        scheduler.shutdown();
        if (!scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }

    }
}

package org.example.sync.executeService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleCallableExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        Callable<String> job1 = () -> {
            System.out.println("Job Run");

            return "End";
        };

        ScheduledFuture<String> schedule = scheduler.schedule(job1, 3, TimeUnit.SECONDS);

        while (!schedule.isDone()) {
            long delay = schedule.getDelay(TimeUnit.MILLISECONDS);
            System.out.println("delay = " + delay);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (schedule.isDone() && !schedule.isCancelled()) {
            try {
                System.out.println("Result : " + schedule.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else{
            System.out.println("Cancelled");
        }


        scheduler.shutdown();
        if (!scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }

    }
}

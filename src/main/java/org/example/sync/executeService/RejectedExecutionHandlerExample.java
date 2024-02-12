package org.example.sync.executeService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectedExecutionHandlerExample {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.AbortPolicy());

        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.CallerRunsPolicy());

        ThreadPoolExecutor executor3 = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardPolicy());

        ThreadPoolExecutor executor4 = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 20; i++) {
            final int taskNum = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "   0=>  Running..." + taskNum);
            });
            executor2.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "   0=>  Running..." + taskNum);
            });
            executor3.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "   0=>  Running..." + taskNum);
            });
            executor4.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "   0=>  Running..." + taskNum);
            });
        }

        executor.shutdown();
        executor2.shutdown();
        executor3.shutdown();
        executor4.shutdown();
    }
}

class MyRejectExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            executor.getQueue().poll(); // 앞에껄 빼고
            executor.getQueue().offer(r); //  하나 넣고
        }
    }
}

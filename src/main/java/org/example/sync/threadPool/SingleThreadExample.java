package org.example.sync.threadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleThreadExample {
    private final int numThread;
    private final Queue<Runnable> tasks;
    private final List<Thread> threads;
    private AtomicBoolean isShutDown;

    public SingleThreadExample(final int numThread) {
        this.numThread = numThread;
        this.tasks = new LinkedList<>();
        this.threads = new ArrayList<>();
        this.isShutDown = new AtomicBoolean(false);

        for (int i = 0; i < numThread; i++) {
            WorkerThread e = new WorkerThread();
            this.threads.add(e);
            e.start();
        }
    }

    public void submit(final Runnable task) {
        if (!isShutDown.get()) {
            synchronized (tasks) {
                this.tasks.offer(task);
                this.tasks.notifyAll();
            }
        }
    }

    public void shutdown() {
        this.isShutDown.set(true);
        synchronized (tasks) {
            tasks.notifyAll();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                thread.interrupt();
            }
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutDown.get()) {
                synchronized (tasks) {
                    while (tasks.isEmpty() && !isShutDown.get()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
                if (!tasks.isEmpty()) {
                    Runnable poll = tasks.poll();
                    poll.run();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        SingleThreadExample singleThreadExample = new SingleThreadExample(10);
        for (int i = 0; i < 10; i++) {
            int taskId = i;
            singleThreadExample.submit(() -> {
                final int threadTaskId = taskId;
                System.out.println(Thread.currentThread().getName() + " : Work -> " + threadTaskId + " Running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            });
        }

        Thread.sleep(3000);

        singleThreadExample.shutdown();
    }
}

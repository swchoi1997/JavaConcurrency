package org.example.sync.executeService;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> taskQueue = new LinkedBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Producing " + i);
                try {
                    taskQueue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Integer take = taskQueue.take();
                    System.out.println("Consuming " + take);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumer.start();

    }
}

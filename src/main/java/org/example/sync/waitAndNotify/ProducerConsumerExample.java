package org.example.sync.waitAndNotify;

import java.util.LinkedList;
import java.util.List;

class SharedQueue {
    private static final int CAPACITY = 5;
    private LinkedList<Integer> queue = new LinkedList<>();
    private final Object lock = new Object();


    public void produce(final int item) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() >= CAPACITY) {
                System.out.println("Queue is Full Stop Produce : " + Thread.currentThread().getName());
                lock.wait();
            }

            queue.add(item);
            System.out.println("produce : " + item);
            lock.notifyAll();
        }
    }

    public int consume() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println("Queue is Empty Stop Concume : " + Thread.currentThread().getName());
                lock.wait();
            }

            Integer item = queue.poll();
            System.out.println("consume : " + item);
            lock.notifyAll();

            return item;
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) throws InterruptedException {
        SharedQueue queue = new SharedQueue();

        Thread produceThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Produce Thread");

        Thread consumeThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.consume();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Consume Thread");

        produceThread.start();
        consumeThread.start();

        produceThread.join();
        consumeThread.join();
    }


}

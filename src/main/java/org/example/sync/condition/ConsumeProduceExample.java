package org.example.sync.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumeProduceExample {

    private static final Integer MAX_QUEUE_SIZE = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final LinkedList<Integer> queue = new LinkedList<>();

    private boolean running = true;


    public void producer() {
        int count = 0;
        while (running) {
            lock.lock();
            try {
                while (queue.size() >= MAX_QUEUE_SIZE) {
                    System.out.println("Queue is Full | Size -> " + this.queue.size());
                    condition1.await();
                }
                this.queue.add(count);
                count++;
                condition2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }

    public void consumer() {
        while (this.checkConsumerIsRunning()) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println("Queue is Empty | Size -> " + this.queue.size());
                    condition2.await();
                }

                Integer poll = this.queue.poll();
                System.out.println("poll = " + poll + " | Queue Size : " + this.queue.size());
                condition1.signal();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();

                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private boolean checkConsumerIsRunning() {
        lock.lock();
        try {

            if (!this.running) {
                if (this.queue.isEmpty()) {
                    return false;
                }
            }
        }finally {
            lock.unlock();
        }
        return true;
    }

    public void stopAll() {
        lock.lock();
        try {
            this.running = false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConsumeProduceExample example = new ConsumeProduceExample();

        Thread consumer1 = new Thread(example::consumer);
        Thread producer1 = new Thread(example::producer);
        Thread producer2 = new Thread(example::producer);

        consumer1.start();
        producer1.start();
        producer2.start();

        Thread.sleep(10000);
        System.out.println("STOPPPPPPPPPPPPPPPP");
        example.stopAll();

        consumer1.join();
        producer1.join();
        producer2.join();

    }
}

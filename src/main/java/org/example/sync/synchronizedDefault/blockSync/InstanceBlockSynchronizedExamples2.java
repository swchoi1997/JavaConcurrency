package org.example.sync.synchronizedDefault.blockSync;

public class InstanceBlockSynchronizedExamples2 {

    private final Object lock = new Object();
    private int count = 0;

    public void increment() {
        synchronized (this) {
            this.count++;
            System.out.println(Thread.currentThread().getName() + " is Increment This Block | count -> " + this.count);
        }
    }

    public void incrementLockObject() {
        synchronized (lock) {
            this.count++;
            System.out.println(Thread.currentThread().getName() + " is Increment Lock Block | count -> " + this.count);
        }
    }

    public void decrement() {
        synchronized (this) {
            this.count--;
            System.out.println(Thread.currentThread().getName() + " is Decrement This Block | count -> " + this.count);
        }
    }

    public void decrementLockObject() {
        synchronized (lock) {
            this.count--;
            System.out.println(Thread.currentThread().getName() + " is Decrement Lock Block | count -> " + this.count);
        }
    }

    public int getCount() {
        return this.count;
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceBlockSynchronizedExamples2 examples = new InstanceBlockSynchronizedExamples2();
        InstanceBlockSynchronizedExamples2 examples2 = new InstanceBlockSynchronizedExamples2();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples2.decrement();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples2.incrementLockObject();
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples.decrementLockObject();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("Finally -> " + examples.getCount());
        System.out.println("Finally -> " + examples2.getCount());

    }
}

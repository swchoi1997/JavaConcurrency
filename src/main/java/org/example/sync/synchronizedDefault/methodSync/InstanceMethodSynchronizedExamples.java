package org.example.sync.synchronizedDefault.methodSync;

public class InstanceMethodSynchronizedExamples {

    private int count = 0;

    public synchronized void increment() {
        this.count++;
        System.out.println("Count Increment -> " + count);
    }

    public synchronized void decrement() {
        this.count--;
        System.out.println("Count Decrement -> " + count);
    }

    public int getCount() {
        return this.count;
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceMethodSynchronizedExamples examples = new InstanceMethodSynchronizedExamples();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples.decrement();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Finally -> " + examples.getCount());

    }
}

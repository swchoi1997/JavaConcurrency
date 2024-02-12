package org.example.sync.synchronizedDefault.methodSync;

public class InstanceMethodSynchronizedExamples2 {

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
        InstanceMethodSynchronizedExamples2 examples = new InstanceMethodSynchronizedExamples2();
        InstanceMethodSynchronizedExamples2 examples2 = new InstanceMethodSynchronizedExamples2();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples.increment();
                examples2.decrement();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                examples.decrement();
                examples2.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Finally examples Count -> " + examples.getCount());
        System.out.println("Finally examples2 Count -> " + examples2.getCount());

    }
}

package org.example.sync.synchronizedDefault.methodSync;

public class InstanceStaticMethodSynchronizedExamples {

    private static int count = 0;

    public static synchronized void increment() {
        count++;
        System.out.println("Count Increment -> " + count);
    }

    public static synchronized void decrement() {
        count--;
        System.out.println("Count Decrement -> " + count);
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                InstanceStaticMethodSynchronizedExamples.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                InstanceStaticMethodSynchronizedExamples.decrement();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Finally -> " + InstanceStaticMethodSynchronizedExamples.getCount());

    }
}

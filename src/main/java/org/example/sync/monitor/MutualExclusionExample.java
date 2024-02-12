package org.example.sync.monitor;

public class MutualExclusionExample {

    private int count = 0;
    public void increment() {
        synchronized (MutualExclusionExample.class) {
            count++;
            System.out.println("Thread : " + Thread.currentThread().getName() + " -> " + this.count);
        }
    }

    public void increment2() {
        count++;
        System.out.println("Thread : " + Thread.currentThread().getName() + " -> " + this.count);

    }


    public static void main(String[] args) throws InterruptedException {
        MutualExclusionExample example = new MutualExclusionExample();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                example.increment();
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

    }
}

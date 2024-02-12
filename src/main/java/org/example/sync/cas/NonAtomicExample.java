package org.example.sync.cas;

public class NonAtomicExample {

    private static int value = 0;

    private static int NUM_THREAD = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread[] thread = new Thread[NUM_THREAD];

        for (int i = 0; i < NUM_THREAD; i++) {
            thread[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int expectedValue = value;
                    int newValue = expectedValue + 1;

                    value = newValue;
                    System.out.println(Thread.currentThread().getName() + " : " + expectedValue + " : " + newValue);
                }
            });
            thread[i].start();
        }

        for (Thread thread1 : thread) {
            thread1.join();
        }

        System.out.println("Final Value : " + value);
    }
}

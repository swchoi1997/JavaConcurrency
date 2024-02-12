package org.example.sync.monitor;

public class ConditionSynchronizationExample {

    private boolean isAvailable = false;
    public synchronized void produce() {
        while (isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("생산됨");
        isAvailable = true;
        notify();
    }

    public synchronized void consume() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("소비됨");
        isAvailable = false;
        notify();
    }

    public static void main(String[] args) {
        ConditionSynchronizationExample example = new ConditionSynchronizationExample();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.produce();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.consume();
            }
        }).start();
    }
}

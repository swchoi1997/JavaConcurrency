package org.example.sync.volatileEx;

public class VolatileExample2 {
    volatile boolean flag = true;

    private volatile int count;

    public void increment() {
        synchronized (VolatileExample2.class) {
            count++;
        }
    }

    public int get() {
        return count;
    }

    public void decrement() {
        synchronized (VolatileExample2.class) {
            count--;
        }
    }

    public static void main(String[] args) {
        VolatileExample2 example = new VolatileExample2();

        for (int i = 0; i < 5; i++) {

        }
        new Thread(example::increment);
    }
}

package org.example.sync.mutex;

public class Mutex {

    private boolean lock = false;

    public synchronized void acquired() {
        while (lock) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.lock = true;
    }

    public synchronized void release() {
        this.lock = false;
        this.notify();
    }
}

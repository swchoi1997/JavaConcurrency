package org.example.sync.semaphore;

public class BinarySemaphore implements CommonSemaphore {

    private int signal = 1;
    @Override

    public synchronized void acquired() throws InterruptedException {
        while (signal == 0) {
            wait();
        }

        this.signal = 0;


    }

    @Override
    public synchronized void release() {
        this.signal = 1;
        this.notifyAll();

    }
}

package org.example.sync.semaphore;

public class CountingSemaphore implements CommonSemaphore {

    private int signal;
    private int permit;

    public CountingSemaphore(final int permit) {
        this.signal = permit;
        this.permit = permit;
    }

    @Override

    public synchronized void acquired() throws InterruptedException {
        while (signal == 0) {
            wait();
        }

        this.signal--;
    }

    @Override
    public synchronized void release() {
        if (this.signal < this.permit) {
            this.signal++;
            this.notifyAll();
        }

    }
}

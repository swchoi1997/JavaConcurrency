package org.example.sync.semaphore;

public interface CommonSemaphore {

    void acquired() throws InterruptedException;

    void release();
}

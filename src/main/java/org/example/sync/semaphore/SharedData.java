package org.example.sync.semaphore;

import org.example.sync.mutex.Mutex;

public class SharedData {

    private int sharedValue = 0;

    private CommonSemaphore commonSemaphore;

    public SharedData(final CommonSemaphore commonSemaphore) {
        this.commonSemaphore = commonSemaphore;
    }

    public void sum() {
        try{
            commonSemaphore.acquired();
            for (int i = 0; i < 10000000; i++) {
                this.sharedValue++;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            commonSemaphore.release();
        }
    }

    public void sum2() {
        for (int i = 0; i < 10000000; i++) {
            try {
                commonSemaphore.acquired();
                this.sharedValue++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                commonSemaphore.release();
            }
        }
    }

    public int get() {
        return this.sharedValue;
    }
}

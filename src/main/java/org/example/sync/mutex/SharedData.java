package org.example.sync.mutex;

public class SharedData {

    private int sharedValue = 0;

    private Mutex mutex;

    public SharedData(final Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try{
            mutex.acquired();
            for (int i = 0; i < 10000000; i++) {
                this.sharedValue++;
            }
        } finally {
            mutex.release();
        }
    }

    public void sum2() {
        for (int i = 0; i < 10000000; i++) {
            try {
                mutex.acquired();
                this.sharedValue++;
            }finally {
                mutex.release();
            }
        }
    }

    public int get() {
        return this.sharedValue;
    }
}

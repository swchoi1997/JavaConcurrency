package org.example.sync.synchronizedDefault;

public class SynchronizedExample {

    private int instanceCnt = 0;
    private static int staticCnt = 0;


    public synchronized void instanceMethod() {
        this.instanceCnt++;
        System.out.println("Instance Method Synchronized " + instanceCnt);
    }

    public static synchronized void staticMethod() {
        SynchronizedExample.staticCnt++;
        System.out.println("Static Method Synchronized " + staticCnt);
    }

    public void instanceBlock() {
        synchronized (this) {
            this.instanceCnt++;
            System.out.println("Instance Block Synchronized " + instanceCnt);
        }
    }

    public static void staticBlock() {
        synchronized (SynchronizedExample.class) {
            SynchronizedExample.staticCnt++;
            System.out.println("Static Block Synchronized " + staticCnt);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample = new SynchronizedExample();

        new Thread(synchronizedExample::instanceMethod).start();
        new Thread(synchronizedExample::instanceBlock).start();
        new Thread(SynchronizedExample::staticMethod).start();
        new Thread(SynchronizedExample::staticBlock).start();

    }
}

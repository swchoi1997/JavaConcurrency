package org.example.sync.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numThread = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(numThread);

        for (int i = 0; i <numThread; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        Thread.sleep(3000);

        startSignal.countDown();

        doneSignal.await();

        System.out.println(Thread.currentThread().getName() + " : All Thread Work Is Done");

    }

    static class Worker implements Runnable{
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        public Worker(final CountDownLatch startSignal, final CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }


        @Override
        public void run() {
            try {
                startSignal.await();
                System.out.println(Thread.currentThread().getName() + " Working");

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " Is Done");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                doneSignal.countDown();
            }
        }
    }
}

package org.example.sync.countDownLatch;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    private static int[] parallelSum = new int[2];

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int numThread = 2;

        CyclicBarrier barrier = new CyclicBarrier(numThread, new BarrierAction(parallelSum));

        for (int i = 0; i < numThread; i++) {
            new Thread(new Worker(i, numbers, barrier, parallelSum)).start();
        }
    }

}

class BarrierAction implements Runnable{

    private final int[] parallelSum;

    public BarrierAction(final int[] parallelSum) {
        this.parallelSum = parallelSum;
    }

    @Override
    public void run() {
        System.out.println(Arrays.toString(parallelSum));
        int sum = Arrays.stream(parallelSum).sum();
        System.out.println("sum = " + sum);
    }
}


class Worker implements Runnable {
    private final int i;
    private final int[] numbers;
    private final CyclicBarrier barrier;
    private final int[] parallelSum;

    public Worker(final int i, final int[] numbers, final CyclicBarrier barrier, final int[] parallelSum) {
        this.i = i;
        this.numbers = numbers;
        this.barrier = barrier;
        this.parallelSum = parallelSum;
    }

    @Override
    public void run() {
        System.out.println("나 여기 들어옴");

        int start = i * (numbers.length / 2);
        int end = (i + 1) * (numbers.length / 2);
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }

        parallelSum[i] = sum;

        try {
            barrier.await();
            System.out.println("Wait1");
            System.out.println(barrier.getNumberWaiting());
            barrier.await();
            System.out.println(barrier.getNumberWaiting());
            System.out.println("Wait2");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }


    }
}

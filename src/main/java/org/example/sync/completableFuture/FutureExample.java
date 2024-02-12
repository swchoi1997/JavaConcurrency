package org.example.sync.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Integer> submit = executorService.submit(new Service());
        Future<Integer> submit2 = executorService.submit(new Service2(2, submit));
        Future<Integer> submit3 = executorService.submit(new Service2(3, submit2));
        Future<Integer> submit4 = executorService.submit(new Service2(4, submit3));
        Future<Integer> submit5 = executorService.submit(new Service2(5, submit4));
        Future<Integer> submit6 = executorService.submit(new Service2(6, submit5));

        try {
            Integer i = submit6.get();
            System.out.println("submit6 - i = " + i);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

    }

    static class Service implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 1 Start");

            return 1;
        }
    }

    static class Service2 implements Callable<Integer> {

        private final Integer serviceName;
        private final Future<Integer> future;

        public Service2(final Integer serviceName, final Future<Integer> future) {
            this.serviceName = serviceName;
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service" + this.serviceName + " Start");

            return future.get() + 2;
        }
    }
}

package org.example.sync.mutex;

public class MutexExample {

    public static void main(String[] args) throws InterruptedException {

        SharedData sharedData = new SharedData(new Mutex());

        Thread thread1 = new Thread(sharedData::sum);
        Thread thread2 = new Thread(sharedData::sum);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("result = " + sharedData.get());

        SharedData sharedData2 = new SharedData(new Mutex());

        Thread thread3 = new Thread(sharedData2::sum2);
        Thread thread4 = new Thread(sharedData2::sum2);

        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        System.out.println("result2 = " + sharedData.get());


    }
}

package org.example.sync.readWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedData data = new SharedData();

        Thread readerThread1 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Reader1 Thread Read Data -> " + data.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread readerThread2 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Reader2 Thread Read Data -> " + data.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });


        Thread writerThread = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("Writer Thread write Data -> " + data.getData());
                data.addData(10);
                Thread.sleep(2000);
                System.out.println("Writer Thread Change Data -> " + data.getData());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        readerThread1.start();
        writerThread.start();
        Thread.sleep(100);
        readerThread2.start();

        readerThread1.join();
        readerThread2.join();
        writerThread.join();



    }



    static class SharedData {
        private int data = 0;

        public int getData() {
            return data;
        }

        public void addData(int value) {
            this.data += value;
        }
    }
}

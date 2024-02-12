package org.example.sync.readWriteLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockReadLockExample {

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        BankAccount account = new BankAccount(lock);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Current balance -> " + account.getBalence());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            final int count = i;
            new Thread(() -> {
                try {
                    account.deposit("sangwon" + count, 10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Deposit");
            }).start();
        }
        Thread.sleep(100);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Current balance -> " + account.getBalence());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }


        System.out.println(account.getBalence());
    }


}


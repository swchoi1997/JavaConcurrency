package org.example.sync.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Stream;

public class BankAccount {
    private final ReadWriteLock lock;
    private volatile Map<String, Integer> balance = new HashMap<>();


    public BankAccount(final ReadWriteLock lock) {
        this.lock = lock;
        this.balance.put("account1", 0);
    }

    public int getBalence() throws InterruptedException {
        lock.readLock().lock();
        try {
            Thread.sleep(1000);
            return this.balance.values().stream().mapToInt(Integer::intValue).sum();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void deposit(final String name, final Integer money) throws InterruptedException {
        lock.writeLock().lock();
        try {
            Thread.sleep(2000);
            this.balance.put(name, money);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "lock=" + lock +
                ", balance=" + balance +
                '}';
    }
}

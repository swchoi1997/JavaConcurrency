package org.example.sync.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean flag = false;

    public void awaiting() {
        lock.lock();
        try{
            while (!flag) {
                System.out.println(Thread.currentThread().getName() + " Waiting....");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " Wake Up ~~ !");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void signaling() {
        lock.lock();
        try{
            flag = true;
            System.out.println(Thread.currentThread().getName() + " Signaling !!!!");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ConditionExample conditionExample = new ConditionExample();

        new Thread(conditionExample::awaiting).start();
        Thread.sleep(3000);
        new Thread(conditionExample::signaling).start();
    }
}

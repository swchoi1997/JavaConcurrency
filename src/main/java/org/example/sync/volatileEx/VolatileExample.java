package org.example.sync.volatileEx;

public class VolatileExample {
    volatile boolean flag = true;

    public void volateilTest() {
        new Thread(() -> {
            int count = 0;
            while (flag) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " is Exit | count -> " + count);

        }).start();

        new Thread(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = false;

        }).start();
    }

    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();
        example.volateilTest();
    }
}

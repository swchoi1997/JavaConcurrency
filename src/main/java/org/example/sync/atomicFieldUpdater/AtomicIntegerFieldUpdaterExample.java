package org.example.sync.atomicFieldUpdater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater<MyClass> updater;
    private static AtomicReferenceFieldUpdater<MyClass, String> updater2;

    public static void main(String[] args) {
        updater = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "field1");
        updater2 = AtomicReferenceFieldUpdater.newUpdater(MyClass.class, String.class, "field2");

        MyClass instance = new MyClass();
        int before = updater.getAndSet(instance, 10);
        int after = updater.get(instance);
        System.out.print("before = " + before + " ");
        System.out.println("after = " + after);

        updater2.compareAndSet(instance, null, "Hello");
        System.out.println("myclass String field value -> " + updater2.get(instance));

    }
    static class MyClass {

        private volatile int field1;
        private volatile String field2;



    }

}

package org.example.sync.synchronizedDefault;

class Parent {
    public void print() {
        synchronized (this) {
            System.out.println("Parent Method");
        }
    }
}

class Child extends Parent {
    @Override
    public void print() {
        synchronized (this) {
            System.out.println("Child Method Before");
            super.print();
            System.out.println("Child Method After");
        }
    }
}

public class SynchronizedFeature {

    public static void main(String[] args) {
        Child child = new Child();
        child.print();
    }
}

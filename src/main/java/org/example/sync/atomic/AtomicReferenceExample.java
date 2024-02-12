package org.example.sync.atomic;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {
    public static void main(String[] args) {
        User origianlAlice = new User("Alice", 15);
        AtomicReference<User> alice = new AtomicReference<>(origianlAlice);
        AtomicReference<User> bob = new AtomicReference<>(new User("Bob", 21));

        new Thread(() -> {
            User caorl = new User("Caorl", 40);
            boolean alice1 = alice.compareAndSet(origianlAlice, caorl);
            if (alice1) {
                System.out.println("Caorl Success");
            } else {
                System.out.println("Caorl Fail");
            }

        }).start();

        new Thread(() -> {
            User caorl2 = new User("Caorl2", 40);
            boolean alice1 = bob.compareAndSet(new User("Bob", 22), caorl2);
            if (alice1) {
                System.out.println("Caorl2 Success");
            } else {
                System.out.println("Caorl2 Fail");
            }

        }).start();

        User user = alice.get();
        User user1 = bob.get();
        System.out.println("user = " + user);
        System.out.println("user1 = " + user1);

    }

}


class User {
    private final String name;
    private final int age;

    public User(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
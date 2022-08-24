package ru.job4j.question;

import java.util.Objects;

public class User implements Comparable<User> {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(User o) {
        int compare = Integer.compare(id, o.id);
        if (compare == 0) {
            compare = name.compareTo(o.name);
        }
        return compare;
    }
}

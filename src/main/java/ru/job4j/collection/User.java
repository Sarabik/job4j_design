package ru.job4j.collection;


import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Ivan", 2, new GregorianCalendar(1995, 4, 3));
        User user2 = new User("Ivan", 2, new GregorianCalendar(1995, 4, 3));
        Map<User, Object> map = new HashMap<>(16);
        Object obj1 = new Object();
        Object obj2 = new Object();
        map.put(user1, obj1);
        map.put(user2, obj2);
        for (Map.Entry<User, Object> elem : map.entrySet()) {
            System.out.println(elem.getKey() + " = " + elem.getValue());
        }

        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
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
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}

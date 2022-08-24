package ru.job4j.question;

import java.util.Set;
import java.util.TreeSet;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Set<User> combineSet = new TreeSet<>(previous);
        combineSet.addAll(current);
        int changed = 0;
        User temp = new User(0, null);
        for (User user : combineSet) {
            if (temp.getId() == user.getId()) {
                changed++;
            }
            temp = user;
        }
        return new Info(combineSet.size() - previous.size() - changed,
                changed,
                combineSet.size() - current.size() - changed);
    }
}

package ru.job4j.question;

import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int remaining = 0;
        for (User user1 : previous) {
            for (User user2 : current) {
                if ((!user1.equals(user2)) && user1.getId() == user2.getId()) {
                    changed++;
                    break;
                } else if (user1.equals(user2)) {
                    remaining++;
                    break;
                }
            }
        }
        return new Info(current.size() - remaining - changed,
                changed,
                previous.size() - changed - remaining);
    }

}

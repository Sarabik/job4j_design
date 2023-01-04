package ru.job4j.ood.srp;

public class Human {
    private final String description;
    private final int age;

    public Human(int age) {
        this.age = age;
        if (age < 45) {
            this.description = "young";
        } else {
            this.description = "old";
        }
    }
}

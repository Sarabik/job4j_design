package ru.job4j.ood.srp;

public class Winner {

    public int getWinner() {
        return (int) (Math.random() * 100) + 1;
    }

    public String announcement(int winner) {
        return String.format("The winner is %s", winner);
    }
}

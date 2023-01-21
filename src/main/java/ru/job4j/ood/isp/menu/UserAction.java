package ru.job4j.ood.isp.menu;

public interface UserAction {
    String name();
    boolean execute(Menu menu, Input input);
}

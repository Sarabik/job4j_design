package ru.job4j.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    private static final String OFFSETS = "----";

    @Override
    public void print(Menu menu) {
        menu.forEach(s -> {
            int offsetCount = s.getNumber().split("\\.").length - 1;
            System.out.println(OFFSETS.repeat(offsetCount) + s.getNumber() + s.getName());
        });
    }
}

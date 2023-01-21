package ru.job4j.ood.isp.menu;

public class ExitAction implements UserAction {

    private final Output out;

    public ExitAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Menu menu, Input input) {
        out.println("Exit Program");
        return false;
    }
}

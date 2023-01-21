package ru.job4j.ood.isp.menu;

public class ShowMenuAction implements UserAction {

    private final Output out;

    public ShowMenuAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show menu";
    }

    @Override
    public boolean execute(Menu menu, Input input) {
        out.println("MENU: ");
        MenuPrinter menuPrinter = new ConsoleMenuPrinter();
        menuPrinter.print(menu);
        return true;
    }
}

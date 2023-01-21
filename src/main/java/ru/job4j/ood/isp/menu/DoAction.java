package ru.job4j.ood.isp.menu;

import java.util.Optional;

public class DoAction implements UserAction {

    private final Output out;

    public DoAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Execute item from menu";
    }

    @Override
    public boolean execute(Menu menu, Input input) {
        out.println("EXECUTE ITEM FROM MENU");
        MenuPrinter menuPrinter = new ConsoleMenuPrinter();
        menuPrinter.print(menu);
        String action = input.askStr("Enter a name of the item from menu to execute: ");
        Optional<Menu.MenuItemInfo> itemInfo = menu.select(action);
        if (itemInfo.isPresent()) {
            itemInfo.get().getActionDelegate().delegate();
        } else {
            out.println("Item with specified name is not found");
        }
        return true;
    }
}

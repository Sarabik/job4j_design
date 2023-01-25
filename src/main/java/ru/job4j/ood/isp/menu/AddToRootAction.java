package ru.job4j.ood.isp.menu;

public class AddToRootAction implements UserAction {

    private final Output out;

    public AddToRootAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add item to menu root";
    }

    @Override
    public boolean execute(Menu menu, Input input) {
        out.println("ADD ITEM TO MENU ROOT");
        String name = input.askStr("Enter name of new item: ");
        menu.add(Menu.ROOT, name, TodoApp.DEFAULT_ACTION);
        out.println("Item is added.");
        return true;
    }
}

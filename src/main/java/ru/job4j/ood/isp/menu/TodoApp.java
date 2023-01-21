package ru.job4j.ood.isp.menu;

import java.util.Arrays;
import java.util.List;

public class TodoApp {
    private final Output output;

    public TodoApp(Output output) {
        this.output = output;
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput();
        Menu menu = new SimpleMenu();

        List<UserAction> actions = Arrays.asList(
                new AddToRootAction(output),
                new AddToParentAction(output),
                new DoAction(output),
                new ShowMenuAction(output),
                new ExitAction(output)
        );
        TodoApp app = new TodoApp(output);
        app.init(input, menu, actions);
    }

    public void init(Input input, Menu menu, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                output.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(menu, input);
        }
    }

    private void showMenu(List<UserAction> actions) {
        output.println(System.lineSeparator());
        output.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            output.println(index + ". " + actions.get(index).name());
        }
    }
}

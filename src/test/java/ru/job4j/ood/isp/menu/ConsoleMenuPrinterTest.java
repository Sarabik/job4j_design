package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class ConsoleMenuPrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private ByteArrayOutputStream output;
    private PrintStream old;

    @BeforeEach
    public void setUpStreams() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testString() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        printer.print(menu);
        String expected = "1.Сходить в магазин" +
                System.lineSeparator() +
                "----1.1.Купить продукты" +
                System.lineSeparator();
        assertThat(expected).isEqualTo(output.toString());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(old);
    }
}
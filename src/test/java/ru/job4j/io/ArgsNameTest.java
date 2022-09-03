package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ArgsNameTest {

    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        String argKey = "Xms";
        assertThatThrownBy(() -> jvm.get(argKey))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Argument \"%s\" does not exist", argKey);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There are no arguments");
    }

    @Test
    void whenThereIsNoKey() {
        String arg = "-=UTF-8";
        assertThatThrownBy(() -> ArgsName.of(new String[] {arg}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Argument \"%s\" should match the required pattern \"-key=value\"", arg);
    }

    @Test
    void whenThereIsNoValue() {
        String arg = "-encoding=";
        assertThatThrownBy(() -> ArgsName.of(new String[] {arg}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Argument \"%s\" should match the required pattern \"-key=value\"", arg);
    }

    @Test
    void whenThereIsNoEqualsSymbol() {
        String arg = "-encoding:UTF-8";
        assertThatThrownBy(() -> ArgsName.of(new String[] {arg}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Argument \"%s\" should match the required pattern \"-key=value\"", arg);
    }

    @Test
    void whenThereIsNoDashSymbol() {
        String arg = "encoding=UTF-8";
        assertThatThrownBy(() -> ArgsName.of(new String[] {arg}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Argument \"%s\" should start with \"-\" symbol", arg);
    }
}
package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

@Disabled("TDD tests")

class MyGeneratorTest {

    @Test
    public void whenProduceStringFromTemplate() {
        Generator generator = new MyGenerator();
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Lida");
        args.put("subject", "you");
        assertThat(generator.produce(template, args)).isEqualTo("I am Lida, Who are you?");
    }

    @Test
    public void whenProduceStringAndOneKeyIsMissing() {
        Generator generator = new MyGenerator();
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Lida");
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Key is not found");
    }

    @Test
    public void whenProduceStringAndRedundantKeyFound() {
        Generator generator = new MyGenerator();
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Lida");
        args.put("subject", "you");
        args.put("name2", "Ivan");
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Redundant key is found");
    }
}
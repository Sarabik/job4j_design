package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void whenParseWithoutExceptions() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("key1=value1", "key2=value2");
        Map<String, String> result = nameLoad.getMap();
        Map<String, String> expected = new HashMap<>(
                Map.of(
                        "key1", "value1",
                        "key2", "value2")
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNameDoesNotContainEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("keyvalue"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void whenNameDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void whenNameDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value");
    }

    @Test
    void whenNameIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }
}
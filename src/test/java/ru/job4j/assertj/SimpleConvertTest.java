package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four");
        assertThat(list).hasSize(4)
                .contains("four")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .allMatch(s -> s.length() > 3)
                .doesNotContainNull()
                .first().isEqualTo("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("1", "1", "4", "22");
        assertThat(set).hasSize(3)
                .contains("22")
                .doesNotContain("2")
                .doesNotContainNull()
                .anyMatch((s -> Integer.valueOf(s) > 10))
                .noneMatch(s -> Integer.valueOf(s) < 0)
                .isNotEmpty();
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three");
        assertThat(map).hasSize(3)
                .isNotEmpty()
                .containsKey("first")
                .doesNotContainValue(10)
                .doesNotContainEntry("first", 3);
    }
}
package ru.job4j.kiss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MaxMinTest {

    private static List<Integer> list;
    private static Comparator<Integer> comparator;
    private static MaxMin test;

    @BeforeAll
    static void init() {
        test = new MaxMin();
        comparator = Comparator.naturalOrder();
        list = new ArrayList<>(List.of(3, 76, 22, 9, 21, 17));
    }

    @Test
    public void whenMax() {
        assertThat(test.max(list, comparator)).isEqualTo(76);
    }

    @Test
    public void whenMin() {
        assertThat(test.min(list, comparator)).isEqualTo(3);
    }

    @Test
    public void whenEmptyList() {
        List<Integer> emptyList = new ArrayList<>();
        assertThat(test.min(emptyList, comparator)).isEqualTo(null);
    }
}
package ru.job4j.kiss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MaxMinTest {

    private List<Integer> list;
    private Comparator<Integer> comparator;

    @BeforeEach
    void init() {
        list = new ArrayList<>(List.of(3, 76, 22, 9, 21, 17));
        comparator = Comparator.naturalOrder();
    }

    @Test
    public void whenMax() {
        MaxMin test1 = new MaxMin();
        assertThat(test1.max(list, comparator)).isEqualTo(76);
    }

    @Test
    public void whenMin() {
        MaxMin test2 = new MaxMin();
        assertThat(test2.min(list, comparator)).isEqualTo(3);
    }
}
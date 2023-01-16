package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TruckTest {
    @Test
    public void whenTrackIsWithSize1() {
        assertThatThrownBy(() -> new Truck("1", 1)).
                isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Truck size must be more than 1");
    }

}
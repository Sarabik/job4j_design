package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.products.Cosmetics;
import ru.job4j.ood.lsp.foodstorage.products.Food;
import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.ExpirationCalculator;
import ru.job4j.ood.lsp.foodstorage.quality.LocalDateExpirationCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    private static final LocalDate today = LocalDate.now();
    private final ExpirationCalculator<LocalDate> expirationCalculator = new LocalDateExpirationCalculator();

    @Test
    public void whenSuccessfullyAddToWarehouse() {
        Store warehouse = new Warehouse(expirationCalculator);
        Product shampoo = new Cosmetics("Shampoo", today.minusDays(15),
                today.plusDays(526), 30, 350.50);
        boolean ifAdded = warehouse.add(shampoo);
        assertThat(ifAdded).isTrue();
        assertThat(warehouse.getAll()).contains(shampoo);
    }

    @Test
    public void whenFailedAddToWarehouse() {
        Store warehouse = new Warehouse(expirationCalculator);
        Product bread = new Food("Bread", today.minusDays(1),
                today.plusDays(2), 15, 45.00);
        boolean ifAdded = warehouse.add(bread);
        assertThat(ifAdded).isFalse();
        assertThat(warehouse.getAll()).isNullOrEmpty();
    }
}
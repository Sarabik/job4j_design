package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.products.Food;
import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.ExpirationCalculator;
import ru.job4j.ood.lsp.foodstorage.quality.LocalDateExpirationCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    private static final LocalDate TODAY = LocalDate.now();
    private final ExpirationCalculator<LocalDate> expirationCalculator = new LocalDateExpirationCalculator();

    @Test
    public void whenSuccessfullyAddToTrash() {
        Store trash = new Trash(expirationCalculator);
        Product butter = new Food("Butter", TODAY.minusDays(60),
                TODAY.plusDays(-2), 7, 210.10);
        boolean ifAdded = trash.add(butter);
        assertThat(ifAdded).isTrue();
        assertThat(trash.getAll()).contains(butter);
    }

    @Test
    public void whenFailedAddToTrash() {
        Store trash = new Trash(expirationCalculator);
        Product bread = new Food("Bread", TODAY.minusDays(1),
                TODAY.plusDays(2), 15, 45.00);
        boolean ifAdded = trash.add(bread);
        assertThat(ifAdded).isFalse();
        assertThat(trash.getAll()).isNullOrEmpty();
    }
}
package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.products.Food;
import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.ExpirationCalculator;
import ru.job4j.ood.lsp.foodstorage.quality.LocalDateExpirationCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    private static final LocalDate TODAY = LocalDate.now();
    private final ExpirationCalculator<LocalDate> expirationCalculator = new LocalDateExpirationCalculator();

    @Test
    public void whenSuccessfullyAddToShopWithoutDiscount() {
        Store shop = new Shop(expirationCalculator);
        Product bread = new Food("Bread", TODAY.minusDays(1),
                TODAY.plusDays(2), 15, 45.00);
        boolean ifAdded = shop.add(bread);
        assertThat(ifAdded).isTrue();
        assertThat(shop.getAll()).contains(bread);
    }

    @Test
    public void whenFailedAddToShop() {
        Store shop = new Shop(expirationCalculator);
        Product butter = new Food("Butter", TODAY.minusDays(60),
                TODAY.plusDays(-2), 7, 210.10);
        boolean ifAdded = shop.add(butter);
        assertThat(ifAdded).isFalse();
        assertThat(shop.getAll()).isNullOrEmpty();
    }

    @Test
    public void whenSuccessfullyAddToShopWithDiscount() {
        Store shop = new Shop(expirationCalculator);
        Product milk = new Food("Milk", TODAY.minusDays(5),
                TODAY.plusDays(1), 25, 76.60);
        Product discountMilk = new Food("Milk", TODAY.minusDays(5),
                TODAY.plusDays(1), 25, 57.45);
        boolean ifAdded = shop.add(milk);
        assertThat(ifAdded).isTrue();
        assertThat(shop.getAll()).contains(discountMilk);
    }
}
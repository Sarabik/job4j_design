package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.products.Cosmetics;
import ru.job4j.ood.lsp.foodstorage.products.Food;
import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.QualityPercent;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class StoreTest {
    public LocalDate today = LocalDate.now();

    @Test
    public void whenSuccessfullyAddToWarehouse() {
        Store warehouse = new Warehouse();
        Product shampoo = new Cosmetics("Shampoo", today.minusDays(15),
                today.plusDays(526), 30, 350.50);
        boolean ifAdded = warehouse.add(shampoo, QualityPercent.getQualityPercent(shampoo));
        assertThat(ifAdded).isTrue();
        assertThat(warehouse.getAll()).contains(shampoo);
    }

    @Test
    public void whenFailedAddToWarehouse() {
        Store warehouse = new Warehouse();
        Product bread = new Food("Bread", today.minusDays(1),
                today.plusDays(2), 15, 45.00);
        boolean ifAdded = warehouse.add(bread, QualityPercent.getQualityPercent(bread));
        assertThat(ifAdded).isFalse();
        assertThat(warehouse.getAll()).isNullOrEmpty();
    }

    @Test
    public void whenSuccessfullyAddToShopWithoutDiscount() {
        Store shop = new Shop();
        Product bread = new Food("Bread", today.minusDays(1),
                today.plusDays(2), 15, 45.00);
        boolean ifAdded = shop.add(bread, QualityPercent.getQualityPercent(bread));
        assertThat(ifAdded).isTrue();
        assertThat(shop.getAll()).contains(bread);
    }

    @Test
    public void whenFailedAddToShop() {
        Store shop = new Shop();
        Product butter = new Food("Butter", today.minusDays(60),
                today.plusDays(-2), 7, 210.10);
        boolean ifAdded = shop.add(butter, QualityPercent.getQualityPercent(butter));
        assertThat(ifAdded).isFalse();
        assertThat(shop.getAll()).isNullOrEmpty();
    }

    @Test
    public void whenSuccessfullyAddToShopWithDiscount() {
        Store shop = new Shop();
        Product milk = new Food("Milk", today.minusDays(5),
                today.plusDays(1), 25, 76.60);
        Product discountMilk = new Food("Milk", today.minusDays(5),
                today.plusDays(1), 25, 57.45);
        boolean ifAdded = shop.add(milk, QualityPercent.getQualityPercent(milk));
        assertThat(ifAdded).isTrue();
        assertThat(shop.getAll()).contains(discountMilk);
    }

    @Test
    public void whenSuccessfullyAddToTrash() {
        Store trash = new Trash();
        Product butter = new Food("Butter", today.minusDays(60),
                today.plusDays(-2), 7, 210.10);
        boolean ifAdded = trash.add(butter, QualityPercent.getQualityPercent(butter));
        assertThat(ifAdded).isTrue();
        assertThat(trash.getAll()).contains(butter);
    }

    @Test
    public void whenFailedAddToTrash() {
        Store trash = new Trash();
        Product bread = new Food("Bread", today.minusDays(1),
                today.plusDays(2), 15, 45.00);
        boolean ifAdded = trash.add(bread, QualityPercent.getQualityPercent(bread));
        assertThat(ifAdded).isFalse();
        assertThat(trash.getAll()).isNullOrEmpty();
    }
}
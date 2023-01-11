package ru.job4j.ood.lsp.foodstorage.quality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.products.Cosmetics;
import ru.job4j.ood.lsp.foodstorage.products.Food;
import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    public List<Product> prodList;
    LocalDate today = LocalDate.now();

    @BeforeEach
    public void fillList() {
        prodList = new ArrayList<>();
        prodList.add(new Food("Milk", today.minusDays(5),
                today.plusDays(1), 25, 76.60));
        prodList.add(new Food("Bread", today.minusDays(1),
                today.plusDays(2), 15, 45.00));
        prodList.add(new Food("Butter", today.minusDays(60),
                today.plusDays(-2), 7, 210.10));
        prodList.add(new Food("Yogurt", today.minusDays(3),
                today.plusDays(75), 15, 135.50));
        prodList.add(new Cosmetics("Shampoo", today.minusDays(15),
                today.plusDays(526), 30, 350.50));
        prodList.add(new Cosmetics("Mascara", today.minusDays(628),
                today.plusDays(-38), 30, 400.00));
    }
    @Test
    public void whenGetWarehouseProducts() {
        ControlQuality controlQuality = new ControlQuality();
        prodList.forEach(controlQuality::sendToStorage);
        List<Product> excepted = Arrays.asList(
                new Food("Yogurt", today.minusDays(3),
                        today.plusDays(75), 15, 135.50),
                new Cosmetics("Shampoo", today.minusDays(15),
                        today.plusDays(526), 30, 350.50));
        assertThat(controlQuality.getWarehouseProducts()).isEqualTo(excepted);
    }

    @Test
    public void whenGetShopProducts() {
        ControlQuality controlQuality = new ControlQuality();
        prodList.forEach(controlQuality::sendToStorage);
        List<Product> excepted = Arrays.asList(
                new Food("Milk", today.minusDays(5),
                        today.plusDays(1), 25, 57.45),
                new Food("Bread", today.minusDays(1),
                        today.plusDays(2), 15, 45.00));
        assertThat(controlQuality.getShopProducts()).isEqualTo(excepted);
    }

    @Test
    public void whenGetTrashProducts() {
        ControlQuality controlQuality = new ControlQuality();
        prodList.forEach(controlQuality::sendToStorage);
        List<Product> excepted = Arrays.asList(
                new Food("Butter", today.minusDays(60),
                        today.plusDays(-2), 7, 210.10),
                new Cosmetics("Mascara", today.minusDays(628),
                        today.plusDays(-38), 30, 400.00));
        assertThat(controlQuality.getTrashProducts()).isEqualTo(excepted);
    }

}
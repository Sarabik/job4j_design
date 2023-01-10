package ru.job4j.ood.lsp.foodstorage;

import ru.job4j.ood.lsp.foodstorage.products.Cosmetics;
import ru.job4j.ood.lsp.foodstorage.products.Food;
import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.ControlQuality;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        ControlQuality controlQuality = new ControlQuality();
        List<Product> products = testList();
        products.forEach(controlQuality::sendToStorage);
        System.out.println("Warehouse");
        System.out.println(controlQuality.getWarehouseProducts());
        System.out.println("Shop");
        System.out.println(controlQuality.getShopProducts());
        System.out.println("Trash");
        System.out.println(controlQuality.getTrashProducts());
    }

    public static List<Product> testList() {
        List<Product> list = new ArrayList<>();
        list.add(new Food("Milk", LocalDate.of(2023, 1, 6),
                LocalDate.of(2023, 2, 22), 25, 76.60));
        list.add(new Food("Bread", LocalDate.of(2023, 1, 4),
                LocalDate.of(2023, 1, 8), 15, 45.00));
        list.add(new Food("Butter", LocalDate.of(2022, 1, 6),
                LocalDate.of(2024, 2, 22), 7, 210.10));
        list.add(new Food("Yogurt", LocalDate.of(2023, 1, 2),
                LocalDate.of(2023, 1, 5), 15, 135.50));
        list.add(new Cosmetics("Shampoo", LocalDate.of(2022, 3, 2),
                LocalDate.of(2030, 1, 20), 30, 350.50));
        list.add(new Cosmetics("Mascara", LocalDate.of(2020, 2, 1),
                LocalDate.of(2022, 7, 25), 30, 400.00));
        return list;
    }
}

package ru.job4j.ood.lsp.foodstorage.products;

import java.time.LocalDate;

public class Cosmetics extends Product {
    public Cosmetics(String name, LocalDate createDate, LocalDate expiryDate, double discount, double price) {
        super(name, createDate, expiryDate, discount, price);
    }
}

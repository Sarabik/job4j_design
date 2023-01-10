package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.util.List;

public abstract class AbstractStore implements Store {
    List<Product> products;

    @Override
    public List<Product> getAll() {
        return products;
    }
}

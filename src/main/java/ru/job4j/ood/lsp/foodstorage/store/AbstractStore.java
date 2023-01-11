package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }
}

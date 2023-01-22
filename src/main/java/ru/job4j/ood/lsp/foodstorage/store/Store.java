package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.util.List;

public interface Store {

    boolean add(Product product);
    List<Product> getAll();
    void emptyStore();
}

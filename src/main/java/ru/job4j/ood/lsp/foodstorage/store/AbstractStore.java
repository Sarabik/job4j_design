package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Product> products = new ArrayList<>();

    @Override
    public boolean add(Product product) {
        boolean result = false;
        if (isFresh(product)) {
            result = products.add(product);
        }
        return result;
    }

    protected abstract boolean isFresh(Product product);

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }
}

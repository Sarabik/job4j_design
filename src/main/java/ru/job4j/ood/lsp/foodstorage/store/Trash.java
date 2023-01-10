package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Trash extends AbstractStore {
    private static final double TRASH_MIN = 100d;
    private static final Predicate<Double> TRASH_ADD = q -> q >= TRASH_MIN;

    public Trash() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean add(Product product, double quality) {
        return TRASH_ADD.test(quality) && products.add(product);
    }
}

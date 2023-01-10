package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Warehouse extends AbstractStore {
    private static final double WAREHOUSE_PERCENT = 25d;
    private static final Predicate<Double> ADD_WAREHOUSE = q -> q < WAREHOUSE_PERCENT;

    public Warehouse() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean add(Product product, double quality) {
        return ADD_WAREHOUSE.test(quality) && products.add(product);
    }
}

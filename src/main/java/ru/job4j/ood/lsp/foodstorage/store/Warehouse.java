package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.ExpirationCalculator;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Warehouse extends AbstractStore {
    private final ExpirationCalculator<LocalDate> expirationCalculator;
    private static final double WAREHOUSE_PERCENT = 25d;
    private static final Predicate<Double> ADD_WAREHOUSE = q -> q < WAREHOUSE_PERCENT;

    public Warehouse(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isFresh(Product product) {
        return ADD_WAREHOUSE.test(
                expirationCalculator.calculate(product.getCreateDate(), product.getExpiryDate()));
    }
}

package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.ExpirationCalculator;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Trash extends AbstractStore {
    private final ExpirationCalculator<LocalDate> expirationCalculator;
    private static final double TRASH_MIN = 100d;
    private static final Predicate<Double> TRASH_ADD = q -> q >= TRASH_MIN;

    public Trash(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isFresh(Product product) {
        return TRASH_ADD.test(expirationCalculator.calculate(product.getCreateDate(), product.getExpiryDate()));
    }
}

package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.quality.ExpirationCalculator;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Shop extends AbstractStore {
    private final ExpirationCalculator<LocalDate> expirationCalculator;
    private static final double SHOP_MIN = 25d;
    private static final double DISCOUNT_BOUND = 75d;
    private static final double SHOP_MAX = 100d;
    private static final Predicate<Double> DISCOUNT_ADD = q -> (q >= DISCOUNT_BOUND) && (q < SHOP_MAX);
    private static final Predicate<Double> SHOP_ADD = q -> (q >= SHOP_MIN) && (q < SHOP_MAX);

    public Shop(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    public boolean add(Product product) {
        boolean result;
        if (DISCOUNT_ADD.test(expirationCalculator.calculate(product.getCreateDate(), product.getExpiryDate()))) {
            result = super.add(applyDiscount(product));
        } else {
            result = super.add(product);
        }
        return result;
    }

    @Override
    protected boolean isFresh(Product product) {
        return SHOP_ADD.test(expirationCalculator.calculate(product.getCreateDate(), product.getExpiryDate()));
    }

    private Product applyDiscount(Product product) {
        product.setPrice(Math.round(product.getPrice() * (1 - product.getDiscount() * 0.01) * 100) / 100.0);
        return product;
    }
}

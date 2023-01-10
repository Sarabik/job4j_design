package ru.job4j.ood.lsp.foodstorage.store;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Shop extends AbstractStore {
    private static final double SHOP_MIN = 25d;
    private static final double DISCOUNT_BOUND = 75d;
    private static final double SHOP_MAX = 100d;
    private static final Predicate<Double> SHOP_ADD = q -> (q >= SHOP_MIN) && (q < DISCOUNT_BOUND);
    private static final Predicate<Double> DISCOUNT_ADD = q -> (q >= DISCOUNT_BOUND) && (q < SHOP_MAX);

    public Shop() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean add(Product product, double quality) {
        boolean result = false;
        if (SHOP_ADD.test(quality)) {
            result = products.add(product);
        } else if (DISCOUNT_ADD.test(quality)) {
            result = products.add(applyDiscount(product));
        }
        return result;
    }

    private Product applyDiscount(Product product) {
        product.setPrice(Math.round(product.getPrice() * (1 - product.getDiscount() * 0.01) * 100) / 100.0);
        return product;
    }
}

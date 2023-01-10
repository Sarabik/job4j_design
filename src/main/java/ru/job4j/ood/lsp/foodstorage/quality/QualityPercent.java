package ru.job4j.ood.lsp.foodstorage.quality;

import ru.job4j.ood.lsp.foodstorage.products.Product;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class QualityPercent {

    public static double getQualityPercent(Product product) {
        long diffTotal = ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
        long diffNow = ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now());
        return (diffNow * 100.0 / diffTotal);
    }
}

package ru.job4j.ood.lsp.foodstorage.quality;

public interface ExpirationCalculator<T> {
    double calculate(T startDate, T endDate);
}

package ru.job4j.ood.lsp.foodstorage.quality;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LocalDateExpirationCalculator implements ExpirationCalculator<LocalDate> {

    @Override
    public double calculate(LocalDate startDate, LocalDate endDate) {
        long diffTotal = ChronoUnit.DAYS.between(startDate, endDate);
        long diffNow = ChronoUnit.DAYS.between(startDate, LocalDate.now());
        return (diffNow * 100.0 / diffTotal);
    }
}

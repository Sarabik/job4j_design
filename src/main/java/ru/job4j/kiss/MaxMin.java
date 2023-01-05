package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, i -> i < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, i -> i > 0);
    }

    private <T> T maxMin(List<T> value, Comparator<T> comparator, Predicate<Integer> mm) {
        T result = null;
        if (value.size() > 0) {
            result = value.get(0);
            for (T element : value) {
                if (mm.test(comparator.compare(result, element))) {
                    result = element;
                }
            }
        }
        return result;
    }
}

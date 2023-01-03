package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    private static final int MAXIMUM = -1;
    private static final int MINIMUM = 1;

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, MAXIMUM);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, MINIMUM);
    }

    private <T> T maxMin(List<T> value, Comparator<T> comparator, int mm) {
        T result = null;
        if (value.size() > 0) {
            result = value.get(0);
            for (T element : value) {
                if (comparator.compare(result, element) == mm) {
                    result = element;
                }
            }
        }
        return result;
    }
}

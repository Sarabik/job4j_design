package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    private static final boolean MAXIMUM = true;
    private static final boolean MINIMUM = false;

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, MAXIMUM);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, MINIMUM);
    }

    private <T> T maxMin(List<T> value, Comparator<T> comparator, boolean mm) {
        List<T> sortedList = new ArrayList<>(value);
        sortedList.sort(comparator);
        return mm ? sortedList.get(value.size() - 1) : sortedList.get(0);
    }
}

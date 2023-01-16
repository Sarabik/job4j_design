package ru.job4j.ood.dip.mistakes;

public class Example1 {
    public interface Sum {
        int getSum(int a, int b);
    }

    public class SumInt implements Sum {

        @Override
        public int getSum(int a, int b) {
            return a + b;
        }
    }
}

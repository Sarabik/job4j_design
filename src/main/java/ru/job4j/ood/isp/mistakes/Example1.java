package ru.job4j.ood.isp.mistakes;

public class Example1 {

    public interface Fighter {
        void hitWithLeg();
        void hitWithHand();
    }

    public class HumanFighter implements Fighter {
        @Override
        public void hitWithLeg() {
            System.out.println("Hit with leg");
        }

        @Override
        public void hitWithHand() {
            System.out.println("Hit with hand");
        }
    }

    public class MermaidFighter implements Fighter {
        @Override
        public void hitWithLeg() {
            throw new UnsupportedOperationException("Mermaids have no legs");
        }

        @Override
        public void hitWithHand() {
            System.out.println("Hit with hand");
        }
    }
}

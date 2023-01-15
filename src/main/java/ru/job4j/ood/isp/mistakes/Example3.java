package ru.job4j.ood.isp.mistakes;

public class Example3 {
    public interface Animal {
        void move();
        void shout();
    }

    public class Dog implements Animal {

        @Override
        public void move() {
            System.out.println("Dog runs");
        }

        @Override
        public void shout() {
            System.out.println("Dog is barking");
        }
    }

    public class Fish implements Animal {

        @Override
        public void move() {
            System.out.println("Fish swims");
        }

        @Override
        public void shout() {
            throw new UnsupportedOperationException("Fish can't shout");
        }
    }
}

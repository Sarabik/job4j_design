package ru.job4j.ood.isp.mistakes;

public class Example2 {
    public interface Weapon {
        void attack();
        void reload();
    }

    public class Gun implements Weapon {

        @Override
        public void attack() {
            System.out.println("Gun shot");
        }

        @Override
        public void reload() {
            System.out.println("Gun reloaded");
        }
    }

    public class Knife implements Weapon {

        @Override
        public void attack() {
            System.out.println("Hit with knife");
        }

        @Override
        public void reload() {
            throw new UnsupportedOperationException();
        }
    }
}

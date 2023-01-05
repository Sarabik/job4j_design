package ru.job4j.ood.ocp.mistakes;

public class Example3 {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        warrior.fight();
    }

    public static class Warrior {
        private final Weapon weapon;

        public Warrior() {
            this.weapon = new Weapon();
        }

        public void fight() {
            weapon.fight();
        }
    }

    public static class Weapon {

        public Weapon() {
        }

        public void fight() {
            System.out.println("Fights with weapon");
        }
    }
}

package ru.job4j.ood.ocp.mistakes;

public class Example2 {
    public static void main(String[] args) {
        Sword sword = new Sword();
        Warrior warrior = new WarriorWithSword(sword);
        warrior.fight();
    }

    public static abstract class Warrior {
        public void fight() {
        }
    }

    public static class WarriorWithSword extends Warrior {
        private final Sword sword;

        public WarriorWithSword(Sword sword) {
            this.sword = sword;
        }

        public void fight() {
            sword.fight();
        }
    }

    public static abstract class Weapon {
        public void fight() {
        }
    }

    public static class Sword extends Weapon {
        @Override
        public void fight() {
            System.out.println("Fights with sword");
        }
    }
}

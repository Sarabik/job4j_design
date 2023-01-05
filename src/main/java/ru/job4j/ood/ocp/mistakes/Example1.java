package ru.job4j.ood.ocp.mistakes;

public class Example1 {
    public static void main(String[] args) {
        Weapon sword = new Weapon("sword");
        Weapon staff = new Weapon("staff");
        Warrior warrior = new Warrior(sword);
        warrior.fight();
        warrior.setWeapon(staff);
        warrior.fight();
    }

    public static class Warrior {
        private Weapon weapon;

        public Warrior(Weapon weapon) {
            this.weapon = weapon;
        }

        public void fight() {
            weapon.fight();
        }

        public void setWeapon(Weapon weapon) {
            this.weapon = weapon;
        }
    }

    public static class Weapon {
        private final String type;

        public Weapon(String type) {
            this.type = type;
        }

        public void fight() {
            if ("sword".equals(type)) {
                System.out.println("Fights with sword");
            } else if ("staff".equals(type)) {
                System.out.println("Fights with staff");
            }
        }
    }
}

package ru.job4j.ood.lsp.mistakes;

public class Example2 {
    public class Fighter {
        private boolean haveLegs;

        public Fighter(boolean haveLegs) {
            this.haveLegs = haveLegs;
        }

        public void hitWithLeg() {
            if (haveLegs) {
                System.out.println("Hit with leg");
            } else {
                System.out.println("Do nothing");
            }
        }
    }

    public class HumanFighter extends Fighter {

        public HumanFighter(boolean haveLegs) {
            super(haveLegs);
        }

        @Override
        public void hitWithLeg() {
            super.hitWithLeg();
        }
    }

    public class MermaidFighter extends Fighter {

        public MermaidFighter(boolean haveLegs) {
            super(haveLegs);
        }

        @Override
        public void hitWithLeg() {
            System.out.println("Hit with leg");
        }
    }
}

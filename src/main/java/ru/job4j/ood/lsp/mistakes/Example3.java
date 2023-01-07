package ru.job4j.ood.lsp.mistakes;

public class Example3 {

    public class Rectangle {
        private int a;
        private int b;

        public Rectangle(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int area() {
            return a * b;
        }
    }

    public class Square extends Rectangle {

        public Square(int a, int b) {
            super(a, b);
            if (a != b) {
                throw new IllegalArgumentException("Sides of the square must be equal");
            }
        }

        @Override
        public int area() {
            return super.area();
        }
    }
}

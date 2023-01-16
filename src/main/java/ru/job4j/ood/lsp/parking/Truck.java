package ru.job4j.ood.lsp.parking;

public class Truck extends Vehicle {

    public Truck(String number, int size) {
        super(number, size);
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("Truck size must be more than 1");
        }
    }
}

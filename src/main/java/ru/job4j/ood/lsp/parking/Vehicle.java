package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public abstract class Vehicle {
    private int size;
    private String number;

    public Vehicle(String number, int size) {
        this.size = size;
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Vehicle{"
                + "size=" + size
                + ", number='" + number + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return size == vehicle.size && Objects.equals(number, vehicle.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, number);
    }
}

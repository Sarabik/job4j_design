package ru.job4j.ood.lsp.parking;

import java.util.Set;

public interface Parking {
    void addVehicle(Vehicle vehicle);
    Set<Vehicle> get();
}

package ru.job4j.ood.lsp.parking;

import java.util.Set;

public interface Parking {
    boolean addVehicle(Vehicle vehicle);
    Set<Vehicle> getAllVehicles();
}

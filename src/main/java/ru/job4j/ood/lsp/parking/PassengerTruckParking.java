package ru.job4j.ood.lsp.parking;

import java.util.*;
import java.util.function.Predicate;

public class PassengerTruckParking implements Parking {
    private List<Vehicle> passengerPlaces;
    private List<Vehicle> trackPlaces;
    private Predicate<Vehicle> ifTrack = v -> v.getSize() > 1;
    private int freeTrackPlaces;
    private int freePassengerCarPlaces;

    public PassengerTruckParking(int passengerPlaceCount, int truckPlaceCount) {
        if (passengerPlaceCount < 0 || truckPlaceCount < 0) {
            throw new IllegalArgumentException("Number of parking places can not be negative");
        }
        this.passengerPlaces = new ArrayList<>();
        this.trackPlaces = new ArrayList<>();
        this.freeTrackPlaces = truckPlaceCount;
        this.freePassengerCarPlaces = passengerPlaceCount;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        boolean ifAdded = false;
        if (ifTrack.test(vehicle) && freeTrackPlaces > 0) {
            ifAdded = trackPlaces.add(vehicle);
            freeTrackPlaces--;
        } else if (freePassengerCarPlaces >= vehicle.getSize()) {
            ifAdded = passengerPlaces.add(vehicle);
            freePassengerCarPlaces -= vehicle.getSize();
        }
        return ifAdded;
    }

    @Override
    public Set<Vehicle> getAllVehicles() {
        Set<Vehicle> set = new HashSet<>(passengerPlaces);
        set.addAll(trackPlaces);
        return set;
    }

    public static void main(String[] args) {
        Parking parking = new PassengerTruckParking(10, 2);
        parking.addVehicle(new PassengerCar("1"));
        parking.addVehicle(new Truck("2", 2));
        parking.addVehicle(new PassengerCar("3"));
        parking.addVehicle(new Truck("4", 2));
        parking.addVehicle(new Truck("5", 3));
        parking.addVehicle(new Truck("6", 2));
        System.out.println(parking.getAllVehicles());
    }

}

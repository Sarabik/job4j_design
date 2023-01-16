package ru.job4j.ood.lsp.parking;

import java.util.*;
import java.util.function.Predicate;

public class PassengerTruckParking implements Parking {
    private final int passengerPlaceCount;
    private final int truckPlaceCount;
    private Vehicle[] passengerPlaces;
    private List<Vehicle> trackPlaces;
    private Predicate<Vehicle> ifTrack = v -> v.getSize() > 1;
    private int freeTrackPlaces;

    public PassengerTruckParking(int passengerPlaceCount, int truckPlaceCount) {
        this.passengerPlaceCount = passengerPlaceCount;
        this.truckPlaceCount = truckPlaceCount;
        this.passengerPlaces = new Vehicle[passengerPlaceCount];
        this.trackPlaces = new ArrayList<>();
        this.freeTrackPlaces = truckPlaceCount;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        boolean ifAdded = false;
        if (ifTrack.test(vehicle) && freeTrackPlaces > 0) {
            ifAdded = trackPlaces.add(vehicle);
            freeTrackPlaces--;
            System.out.println(String.format("Vehicle with number %s is added to Truck parking", vehicle.getNumber()));
            System.out.println(trackPlaces);
            System.out.println(System.lineSeparator());
        }
        if (!ifAdded) {
            ifAdded = putVehicleToPassengersParking(vehicle, passengerPlaces);
            if (ifAdded) {
                System.out.println(String.format("Vehicle with number %s is added to Passenger parking",
                        vehicle.getNumber()));
                System.out.println(Arrays.toString(passengerPlaces));
                System.out.println(System.lineSeparator());
            }

        }
        if (!ifAdded) {
            System.out.printf("There is no place for this vehicle with number %s!", vehicle.getNumber());
            System.out.println(System.lineSeparator());
        }
    }

    private List<Integer> findPossiblePlaces(Vehicle vehicle, Vehicle[] vehiclePlaces) {
        List<Integer> indexes = new ArrayList<>();
        int size = 1;
        for (int i = 0; i < vehiclePlaces.length; i++) {
            if (vehiclePlaces[i] == null) {
                if (size >= vehicle.getSize()) {
                    indexes.add(i - size + 1);
                } else {
                    size++;
                }
            } else {
                size = 1;
            }
        }
        return indexes;
    }

    private int getRandomPlace(List<Integer> indexes) {
        Random rand = new Random();
        return indexes.get(rand.nextInt(indexes.size()));
    }

    private boolean putVehicleToPassengersParking(Vehicle vehicle, Vehicle[] vehiclePlaces) {
        List<Integer> indexes = findPossiblePlaces(vehicle, vehiclePlaces);
        boolean result = indexes.size() > 0;
        if (result) {
            int place = getRandomPlace(indexes);
            for (int i = place; i < place + vehicle.getSize(); i++) {
                vehiclePlaces[i] = vehicle;
            }
        }
        return result;
    }

    @Override
    public Set<Vehicle> get() {
        Set<Vehicle> set = new HashSet<>(trackPlaces);
        for (Vehicle vehicle : passengerPlaces) {
            if (vehicle != null) {
                set.add(vehicle);
            }
        }
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
        System.out.println(parking.get());
    }

}

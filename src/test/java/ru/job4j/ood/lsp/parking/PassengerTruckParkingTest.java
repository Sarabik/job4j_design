package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class PassengerTruckParkingTest {

    @Test
    public void whenAddPassengerCars() {
        Parking parking = new PassengerTruckParking(5, 2);
        Vehicle car1 = new PassengerCar("1");
        Vehicle car2 = new PassengerCar("2");
        parking.addVehicle(car1);
        parking.addVehicle(car2);
        assertThat(parking.getAllVehicles()).containsExactlyInAnyOrder(car1, car2);
    }

    @Test
    public void whenAddTrucks() {
        Parking parking = new PassengerTruckParking(3, 1);
        Vehicle truck1 = new Truck("1", 5);
        Vehicle truck2 = new Truck("2", 3);
        parking.addVehicle(truck1);
        parking.addVehicle(truck2);
        Set<Vehicle> set = new HashSet<>();
        assertThat(parking.getAllVehicles()).containsExactlyInAnyOrder(truck1, truck2);
    }

    @Test
    public void whenAddPassengerCarsAndTrucks() {
        Parking parking = new PassengerTruckParking(5, 1);
        Vehicle truck1 = new Truck("1", 5);
        Vehicle truck2 = new Truck("2", 3);
        Vehicle car1 = new PassengerCar("1");
        Vehicle car2 = new PassengerCar("2");
        parking.addVehicle(truck1);
        parking.addVehicle(truck2);
        parking.addVehicle(car1);
        parking.addVehicle(car2);

        assertThat(parking.getAllVehicles()).containsExactlyInAnyOrder(truck1, truck2, car1, car2);
    }

    @Test
    public void whenTruckIsTooBig() {
        Parking parking = new PassengerTruckParking(2, 0);
        Vehicle truck1 = new Truck("1", 3);
        parking.addVehicle(truck1);
        assertThat(parking.getAllVehicles()).isEmpty();
    }

}
package ru.job4j.serialization.json.family;

import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private int house;

    public Address(String city, String street, int house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address adress = (Address) o;
        return house == adress.house && Objects.equals(city, adress.city) && Objects.equals(street, adress.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house);
    }
}

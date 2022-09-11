package ru.job4j.serialization.json.family;

import java.util.Arrays;
import java.util.Objects;

public class Family {
    private String surname;
    private String[] names;
    private int membersCount;
    private Address address;
    private boolean hasPet;

    public Family(String surname, String[] names, Address address, boolean hasPet) {
        this.surname = surname;
        this.names = names;
        this.membersCount = names.length;
        this.address = address;
        this.hasPet = hasPet;
    }

    public String getSurname() {
        return surname;
    }

    public String[] getNames() {
        return names;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Family family = (Family) o;
        return membersCount == family.membersCount
                && hasPet == family.hasPet
                && Objects.equals(surname, family.surname)
                && Arrays.equals(names, family.names)
                && Objects.equals(address, family.address);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(surname, membersCount, address, hasPet);
        result = 31 * result + Arrays.hashCode(names);
        return result;
    }
}

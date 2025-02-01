package airport.domain.model;

import java.util.Objects;

public class Passenger {
    private String name;
    private String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getAircraftType() {
        return "PrivateJet";
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        com.airport.domain.model.Passenger passenger = (com.airport.domain.model.Passenger) o;
        return Objects.equals(name, passenger.getName()) && Objects.equals(passportNumber, passenger.getPassportNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passportNumber);
    }
}

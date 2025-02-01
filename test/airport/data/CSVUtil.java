package com.airport.data;

import com.airport.domain.model.*;
import com.airport.domain.reservation.ReservationSystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    private Path path;
    private List<String> linesFromFile;


    public CSVUtil(String pathName, List<String> linesFromFile) {
        this.path = Paths.get(pathName);
        this.linesFromFile = linesFromFile;
    }


    public void loadReservationsFromCSV(ReservationSystem reservationSystem) {
        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeFirst();

            for (String reservation : lines) {
                String[] fields = reservation.split(",");
                String flightNumber = fields[0];
                String departureDate = fields[1];
                String ticketPrice = String.valueOf(new BigDecimal(fields[2]).setScale(2, RoundingMode.HALF_UP));
                String passengerName = fields[3];
                String passportNumber = fields[4];
                String aircraftModel = fields[5];
                String aircraftType = fields[6];

                linesFromFile.add(
                        flightNumber + "," + departureDate + "," + ticketPrice + ","
                                + passengerName + "," + passportNumber + "," + aircraftModel + "," + aircraftType);

                reservationSystem.addReservation(flightNumber, new Passenger(passengerName, passportNumber));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveReservationsToCSV(String flightNumber, ArrayList<Flight> flights, ReservationSystem reservationSystem) {

        String reservationInfo;
        ArrayList<String> reservationData = new ArrayList<>();
        String pName;
        String passpNumber;

        for (Passenger passenger : reservationSystem.getPassengersForFlight(flightNumber)) {
            pName = passenger.getName();
            passpNumber = passenger.getPassportNumber();
            for (Flight flight : flights)
                if (flight.getFlightNumber().equals(flightNumber)) {
                    reservationInfo = flightNumber + "," + flight.getDepartureDate() + "," + flight.getTicketPrice()
                            + "," + pName + "," + passpNumber + "," + flight.getAircraft().getModel()
                            + "," + flight.getAircraft().getClass().getSimpleName() + "\n";
                    reservationData.add(reservationInfo);
                }
        }

        try {
            for (String reservation : reservationData) {
                Files.write(path, reservation.getBytes(), StandardOpenOption.APPEND);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

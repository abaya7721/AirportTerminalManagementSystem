package com.airport;

import com.airport.data.CSVUtil;
import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;
import com.airport.domain.reservation.ReservationSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.addReservation("F1200", new Passenger("Person1", "T10000" ));
        reservationSystem.addReservation("F1200", new Passenger("Person2", "T20000" ));
        reservationSystem.addReservation("G2200", new Passenger("Person3", "T30000" ));

        Aircraft boeing737 = new CommercialAircraft("Boeing 737", 189, 1000, "Spirit");
        Aircraft airbusA320 = new CommercialAircraft("Airbus A320", 180, 950, "Spirit");

        Flight flight1 = new Flight(
                "F1200",
                LocalDate.of(2025, 3, 15),
                new BigDecimal("499.99"),
                boeing737
        );
        Flight flight2 = new Flight(
                "G2200",
                LocalDate.of(2025, 4, 20),
                new BigDecimal("599.99"),
                airbusA320
        );

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        System.out.println(reservationSystem.getPassengersForFlight("G2200"));

        ArrayList<String> reservationLines = new ArrayList<>();
        reservationLines.add("AA101,2024-05-10,299.99,Alice Smith,P12345,Boeing 737,Commercial");
        reservationLines.add("BB202,2024-06-15,450.50,John Doe,P67890,Airbus A320,Commercial");
        reservationLines.add("PJ001,2024-07-20,5000.00,None,None,Gulfstream G650,PrivateJet");

        CSVUtil csvUtility = new CSVUtil("./data/reservations.csv", reservationLines);
        csvUtility.loadReservationsFromCSV(reservationSystem);
        csvUtility.saveReservationsToCSV("F1200", flights, reservationSystem);

        for(String line : reservationLines)
            System.out.println(line);

        System.out.println(reservationSystem.getPassengersForFlight("BB102"));
    }
}

package com.airport;

import com.airport.domain.model.Passenger;
import com.airport.domain.reservation.ReservationSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, ArrayList<Passenger>> reservations = new HashMap<>();

        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.addReservation("F1200", new Passenger("Person1", "T10000" ));
        reservationSystem.addReservation("F1200", new Passenger("Person2", "T20000" ));
        reservationSystem.addReservation("G2200", new Passenger("Person3", "T30000" ));

        System.out.println(reservationSystem.getPassengersForFlight("G2200"));
    }
}

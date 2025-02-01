package com.airport.domain.reservation;

import com.airport.domain.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;


public class ReservationSystem {


    private HashMap<String, ArrayList<Passenger>> reservations = new HashMap<>();

    public void addReservation(String flightNumber, Passenger passenger) {
           if(reservations.containsKey(flightNumber)){
               reservations.get(flightNumber).add(passenger);
           }
           else {
               reservations.put(flightNumber, new ArrayList<>());
               reservations.get(flightNumber).add(passenger);
           }
    }

    public ArrayList<Passenger> getPassengersForFlight(String flightNumber) {
        // Empty ArrayList is returned if flight number isn't found
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (String flight : reservations.keySet()) {
            if (flightNumber.equals(flight)) {
                return reservations.get(flightNumber);
            }
        }
        return passengers;
    }



}

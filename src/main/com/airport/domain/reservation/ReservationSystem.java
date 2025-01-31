package com.airport.domain.reservation;


/*ReservationSystem class:
Attributes: reservations (HashMap: key = flight number,
                          value = list of passengers)

Method: addReservation(flightNumber, passenger)
 1. If the flightNumber exists in the reservations HashMap:
        a. Get the list of passengers for that flight.
        b. Add the new passenger to the list.
 2. Otherwise:
        a. Create a new list of passengers.
        b. Add the new passenger to the list.
        c. Put the list in the reservations HashMap with the flightNumber as the key.

Method: getPassengersForFlight(flightNumber)
 1. Return the list of passengers associated with the given flightNumber from the reservations HashMap. Return
    an empty list if the flight number isn't found.*/


import com.airport.domain.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

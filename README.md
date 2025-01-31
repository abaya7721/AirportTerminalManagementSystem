# AirportTerminalManagementSystem

System to manage flights passengers, and ticket reservations. 
Reservations will be stored in a CSV file for better readability and manual editing.

## Setup

Domain

    Model
    
    Contain data model classes: Aircraft, Flight, and Passenger
    Extend Aircraft to CommercialAircraft and PrivateJet classes

    Reservation

    ReservationSystem Class
    Storing reservations in a HashMap<String, ArrayList<Passenger>> 
    Adds a reservation by flight number for passenger and gets passengers for a specific flight

Data

    CSVUtil
    CSV File Handling Class
    Reads and writes reservations in the format:
        - flightNumber,departureDate,ticketPrice,passengerName,passportNumber,aircraftModel,aircraftType

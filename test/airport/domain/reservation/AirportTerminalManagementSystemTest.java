package airport.domain.reservation;

import com.airport.data.CSVUtil;
import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;
import com.airport.domain.reservation.ReservationSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AirportTerminalManagementSystemTest {

    static final String PATH = "./data/reservations_test.csv";
    static File testFile;

    @Before
    public void create() throws IOException {
        testFile = new File(PATH);
        if (!testFile.exists()) {
            Files.createFile(Paths.get(PATH));
            Files.write(Paths.get(PATH), "flightNumber,departureDate,ticketPrice,passengerName,passportNumber,aircraftModel,aircraftType".getBytes(), StandardOpenOption.APPEND);
        }
    }

        @Test
        public void shouldCreateNewAircraftAndGetAttributes() {
            Aircraft boeing737 = new CommercialAircraft("Boeing 737", 189, 1000, "Spirit");
            Aircraft airbusA320 = new CommercialAircraft("Airbus A320", 180, 950, "Spirit");

            assertEquals(189, boeing737.getCapacity());
            assertEquals("CommercialAircraft", boeing737.getClass().getSimpleName());

            assertEquals("Airbus A320", airbusA320.getModel());
            assertEquals(950, airbusA320.getFuelCapacity(), 0);
        }

        @Test
        public void addReservationShouldSuccessfullyAdd () {

            ReservationSystem reservationSystem = new ReservationSystem();
            ArrayList<Passenger> passengers = new ArrayList<>();
            passengers.add(new Passenger("Person1", "T10000"));
            passengers.add(new Passenger("Person2", "T20000"));

            reservationSystem.addReservation("F1200", new Passenger("Person1", "T10000"));
            reservationSystem.addReservation("F1200", new Passenger("Person2", "T20000"));
            reservationSystem.addReservation("G2200", new Passenger("Person3", "T30000"));

            assertEquals(passengers.size(), reservationSystem.getPassengersForFlight("F1200").size());
        }

        @Test
        public void getPassengersForFlightReturnsListOfPassengers() {
            ReservationSystem reservationSystem = new ReservationSystem();
            ArrayList<Passenger> passengers = new ArrayList<>();
            ArrayList<Passenger> passengers2;
            passengers.add(new Passenger("Person1", "T10000"));
            passengers.add(new Passenger("Person2", "T20000"));

            reservationSystem.addReservation("F1200", new Passenger("Person1", "T10000"));
            reservationSystem.addReservation("F1200", new Passenger("Person2", "T20000"));
            passengers2 = reservationSystem.getPassengersForFlight("F1200");

            assertEquals(passengers.getFirst().hashCode(), passengers2.getFirst().hashCode());
            assertTrue(passengers.equals(passengers2));
        }

        @Test
        public void writingFromReservationsToCSVFileShouldUpdateFileWithNewRecords() throws IOException {

            List<String> linesFromFile = new ArrayList<>();
            CSVUtil csvUtil = new CSVUtil(PATH, linesFromFile);
            ReservationSystem reservationSystem = new ReservationSystem();
            reservationSystem.addReservation("F1200", new Passenger("Person1", "T10000"));
            reservationSystem.addReservation("F1200", new Passenger("Person2", "T20000"));
            reservationSystem.addReservation("G2200", new Passenger("Person3", "T30000"));

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

            csvUtil.saveReservationsToCSV("F1200", flights, reservationSystem);

            assertEquals(-1, Files.mismatch(Paths.get(PATH), Paths.get(PATH)));

        }

        @After
        public void tearDown() throws IOException {
                Files.delete(Paths.get(PATH));
        }

    }
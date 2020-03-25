package StepProjectBooking.service;

import StepProjectBooking.database.DAOFlightFileText;
import StepProjectBooking.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    DAOFlightFileText daoFlightFileText;
    FlightService flightService;
    Flight flight = new Flight(13, "Baku", LocalDate.now().plusDays(1), LocalTime.parse(LocalTime.now().plusHours(24).format(DateTimeFormatter.ofPattern("HH:mm:ss"))), 100);

    @BeforeEach
    void setUp() {
        this.daoFlightFileText = new DAOFlightFileText("flightServiceTest.txt");
        this.flightService = new FlightService(daoFlightFileText);
        this.flight = flight;
    }

    @Test
    void getAllFlights() {
        String expected = flight.represent2();
        String actual = flightService.getAllFlights().toString();
        assertEquals(expected, actual);
    }

    @Test
    void getFlightById() {
        String expected = flightService.getFlightById(5);
        String actual = flightService.getFlightById(5);
        assertEquals(expected, actual);
    }

    @Test
    void searchForBook() {
        String expected = "[]";
        String actual = flightService.searchForBook("Baku", LocalDate.now().plusDays(1), 1).toString();
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        String expected = "[]";
        String actual = String.valueOf(flightService.getAll());
        assertEquals(expected, actual);
    }

    @Test
    void addFlight() {
        String expected = flight.represent2();
        String actual = flightService.addFlight1(flight);
        assertEquals(expected,actual);
    }

    @Test
    void showMenu() {
        String expected = "1. Online - board\n2. Show the flight info\n3. Search and book a flight\n4. Cancel the booking\n5. My flights\n6. Exit\n7. End session";
        assertEquals(expected, flightService.showMenu());
    }
}

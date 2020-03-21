package StepProjectBooking.service;

import StepProjectBooking.database.DAOFlightFileText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    private DAOFlightFileText daoFlight = new DAOFlightFileText("flight.txt");
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        this.flightService = new FlightService(daoFlight);
    }

    @Test
    void getAllFlights() {
        String expected = "[7|Ankara|2020-03-22|11:04:19|45, 8|Tbilisi|2020-03-22|14:05:21|15]";
        assertEquals(expected, flightService.getAllFlights().toString());
    }

    @Test
    void getFlightById() {
        String expected = "1|NEW_YORK|2020-03-29|14:38:16|15";
        assertEquals(expected, flightService.getFlightById(1));
    }

    @Test
    void getAll() {
        String expected="[7|Ankara|2020-03-22|11:04:19|45, 8|Tbilisi|2020-03-22|14:05:21|15]";
        assertEquals(expected,flightService.getAllFlights().toString());
    }

    @Test
    void showMenu() {
        String expected = "1. Online - board\n2. Show the flight info\n3. Search and book a flight\n4. Cancel the booking\n5. My flights\n6. Exit\n7. End session";
        assertEquals(expected, flightService.showMenu());
    }
}
package StepProjectBooking.service;

import StepProjectBooking.controller.FlightController;
import StepProjectBooking.database.DAOFlightFileText;
import StepProjectBooking.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    private DAOFlightFileText daoFlight = new DAOFlightFileText("flight.txt");
    private FlightService flightService;
    private Flight flight;
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        this.flightService = new FlightService(daoFlight);
        this.flightController=new FlightController();
        this.flight=flight= new Flight(1500,"Baku", LocalDate.parse("2020-03-22"), LocalTime.parse("10:00"),20);
    }

    @Test
    void getAllFlights() {
        String expected = "[4|Tbilisi|2020-03-22|13:36:05|85, 15|Berlin|2020-03-22|14:23:31|85]";
        assertEquals(expected, flightService.getAllFlights().toString());
    }

    @Test
    void getFlightById() {
        String expected = "20|Berlin|2020-03-30|08:23:27|15";
        assertEquals(expected, flightService.getFlightById(20));
    }

    @Test
    void getAll() {
        String expected = "[4|Tbilisi|2020-03-22|13:36:05|85, 15|Berlin|2020-03-22|14:23:31|85]";
        assertEquals(expected,flightService.getAllFlights().toString());
    }

    @Test
    void showMenu() {
        String expected = "1. Online - board\n2. Show the flight info\n3. Search and book a flight\n4. Cancel the booking\n5. My flights\n6. Exit\n7. End session";
        assertEquals(expected, flightService.showMenu());
    }
}
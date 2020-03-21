package StepProjectBooking.service;

import StepProjectBooking.controller.FlightController;
import StepProjectBooking.database.DAOBookingFileText;
import StepProjectBooking.database.DAOFlightFileText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private DAOFlightFileText daoFlight = new DAOFlightFileText("flight.txt");
    private DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
    private FlightService flightService;
    private BookingService bookingService;
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        this.flightService = new FlightService(daoFlight);
        this.flightController=new FlightController();
        this.bookingService= new BookingService(daoBooking, daoFlight);
    }

    @Test
    void book() {
        String expected = "[]";
        assertEquals(expected, flightService.searchForBook("London",
                LocalDate.parse("2020-03-25"), 1).toString());
    }

    @Test
    void getMyFlights() {
        String expected="[]";
        assertEquals(expected,bookingService.getMyFlights("a","b").toString());
    }
}
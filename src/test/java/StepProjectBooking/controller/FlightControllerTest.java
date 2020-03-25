package StepProjectBooking.controller;

import StepProjectBooking.database.DAOBookingFileText;
import StepProjectBooking.database.DAOFlightFileText;
import StepProjectBooking.entity.Flight;
import StepProjectBooking.io.Console;
import StepProjectBooking.io.ConsoleMain;
import StepProjectBooking.service.BookingService;
import StepProjectBooking.service.FlightService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {
    FlightController flightController;
    DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
    DAOFlightFileText daoFlight = new DAOFlightFileText("flight2.txt");
    BookingService bookingService = new BookingService(daoBooking, daoFlight);
    FlightService flightService = new FlightService(daoFlight);
    Console console = new ConsoleMain();
    Flight flight = new Flight(13, "Baku", LocalDate.now().plusDays(1),
            LocalTime.parse(LocalTime.now().plusHours(24).format(DateTimeFormatter.ofPattern("HH:mm:ss"))),
            100);

    @Test
    void searchForBook() {
        this.flightController = new FlightController(flightService, bookingService, console);
        flightController.addFlight(flight);
        String expected = flight.represent();
        String actual = flightController.searchForBook("Baku", LocalDate.now().plusDays(1), 1);
        assertEquals(expected, actual);
    }
    @Test
    void show(){
        DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
        DAOFlightFileText daoFlight = new DAOFlightFileText("flightShowTest.txt");
        BookingService bookingService = new BookingService(daoBooking,daoFlight);
        FlightService flightService = new FlightService(daoFlight);
        Console console = new ConsoleMain();
        this.flightController = new FlightController(flightService,bookingService,console);
        Flight flight = new Flight("Baku", LocalDate.now().plusDays(1), LocalTime.parse(LocalTime.now().plusHours(24)
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"))), 100);
        flightController.addFlight(flight);
        String expected = flight.represent();
        String actual = flightController.show();
        assertEquals(expected, actual);
    }

    @Test
    void addFlight(){
        DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
        DAOFlightFileText daoFlight = new DAOFlightFileText("addFlightTest.txt");
        BookingService bookingService = new BookingService(daoBooking,daoFlight);
        FlightService flightService = new FlightService(daoFlight);
        Console console = new ConsoleMain();
        this.flightController = new FlightController(flightService,bookingService,console);

        Flight flight1 = new Flight("Baku", LocalDate.of(2020,05,24), LocalTime.of(12,10)/*now().plusHours(2)*/,100);
        Flight flight2 = new Flight("Paris", LocalDate.of(2020,10,10), LocalTime.of(20,00)/*now().plusHours(2)*/,200);

        flightController.addFlight(flight1);
        flightController.addFlight(flight2);

        List<String> expected = new ArrayList(Arrays.asList(flight1.represent(),flight2.represent()));
        List<String> actual = flightController.getAll();

        assertEquals(expected,actual);
    }

}
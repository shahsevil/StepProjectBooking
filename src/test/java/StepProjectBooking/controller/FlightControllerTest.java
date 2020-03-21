package StepProjectBooking.controller;

import StepProjectBooking.database.DAOBookingFileText;
import StepProjectBooking.database.DAOFlightFileText;
import StepProjectBooking.entity.Flight;
import StepProjectBooking.io.Console;
import StepProjectBooking.io.ConsoleMain;
import StepProjectBooking.service.BookingService;
import StepProjectBooking.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {
    FlightController app;


    @Test
    void Test1(){
        DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
        DAOFlightFileText daoFlight = new DAOFlightFileText("flight1.txt");
        BookingService bookingService = new BookingService(daoBooking,daoFlight);
        FlightService flightService = new FlightService(daoFlight);
        Console console = new ConsoleMain();
        this.app = new FlightController(flightService,bookingService,console);
        Flight flight = new Flight("Baki", LocalDate.now(), LocalTime.now().plusHours(2),100);
        app.addFlight(flight);
        String actual = app.show();
        String expected = flight.represent();

        assertEquals(expected,actual);
    }

    @Test
    void Test2(){
        DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
        DAOFlightFileText daoFlight = new DAOFlightFileText("flight2.txt");
        BookingService bookingService = new BookingService(daoBooking,daoFlight);
        FlightService flightService = new FlightService(daoFlight);
        Console console = new ConsoleMain();
        this.app = new FlightController(flightService,bookingService,console);

        Flight flight = new Flight("Baki", LocalDate.of(2020,05,24), LocalTime.of(12,10)/*now().plusHours(2)*/,100);
        app.addFlight(flight);

        String expected = flight.represent();
        String actual = app.searchForBook("Baki", LocalDate.of(2020,05,24), 2);

        assertEquals(expected,actual);
    }

    @Test
    void Test3(){
        DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
        DAOFlightFileText daoFlight = new DAOFlightFileText("flight3.txt");
        BookingService bookingService = new BookingService(daoBooking,daoFlight);
        FlightService flightService = new FlightService(daoFlight);
        Console console = new ConsoleMain();
        this.app = new FlightController(flightService,bookingService,console);

        Flight flight1 = new Flight("Baki", LocalDate.of(2020,05,24), LocalTime.of(12,10)/*now().plusHours(2)*/,100);
        Flight flight2 = new Flight("Paris", LocalDate.of(2020,10,10), LocalTime.of(20,00)/*now().plusHours(2)*/,200);

        app.addFlight(flight1);
        app.addFlight(flight2);

        List<String> expected = new ArrayList(Arrays.asList(flight1.represent(),flight2.represent()));
        List<String> actual = app.getAll();

        assertEquals(expected,actual);
    }




}
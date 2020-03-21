package StepProjectBooking.controller;

import StepProjectBooking.entity.Passenger;
import StepProjectBooking.io.Console;
import StepProjectBooking.service.BookingService;
import StepProjectBooking.service.FlightService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightController {
    FlightService flightService;
    BookingService bookingService;
    Console console;

    public FlightController(FlightService flightService, BookingService bookingService, Console console) {
        this.flightService = flightService;
        this.bookingService = bookingService;
        this.console = console;
    }

    public String show() {
        return String.join("\n", flightService.getAllFlights());
    }

    public String search() {
        console.print("Enter flight id: ");
        return flightService.getFlightById(Integer.parseInt(console.readLn()));
    }

    public String searchForBook(String dest, LocalDate date, int numOfPeople) {
        return String.join("\n", flightService.searchForBook(dest.toLowerCase(), date, numOfPeople));
    }

    public void book(String flightId, int numOfPeople) {
        List<Passenger> passengers = new ArrayList<>();
        while (numOfPeople-- > 0) {
            console.print("Enter name: ");
            String name = console.readLn();
            console.print("Enter surname: ");
            String surname = console.readLn();
            passengers.add(new Passenger(name, surname));
        }
        flightService.book(Integer.parseInt(flightId), passengers);
    }

    public void addFlight() {
        flightService.addFlight();
    }

    public boolean getAll() {
        return flightService.getAll();
    }

    public String showMenu() {
        return flightService.showMenu();
    }
}

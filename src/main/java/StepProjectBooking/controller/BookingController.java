package StepProjectBooking.controller;

import StepProjectBooking.entity.Passenger;
import StepProjectBooking.io.Console;
import StepProjectBooking.service.BookingService;
import StepProjectBooking.service.FlightService;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    BookingService bookingService;
    FlightService flightService;
    Console console;

    public BookingController(BookingService bookingService, FlightService flightService, Console console) {
        this.bookingService = bookingService;
        this.flightService = flightService;
        this.console = console;
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
        bookingService.book(Integer.parseInt(flightId), passengers);
    }

    public String cancelBooking(int id) {
        return bookingService.cancelBooking(id);
    }

    public String getMyFlights() {
        console.print("Enter name: ");
        String name = console.readLn();
        console.print("Enter surname: ");
        String surname = console.readLn();
        return String.join("\n", bookingService.getMyFlights(name.toLowerCase().trim(), surname.toLowerCase().trim()));
    }

}

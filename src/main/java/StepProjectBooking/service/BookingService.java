package StepProjectBooking.service;

import StepProjectBooking.database.DAOBookingFileText;
import StepProjectBooking.database.DAOFlightFileText;
import StepProjectBooking.entity.Booking;
import StepProjectBooking.entity.Flight;
import StepProjectBooking.entity.Passenger;
import StepProjectBooking.predicates.Predicates;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {

    DAOBookingFileText daoBooking;
    DAOFlightFileText daoFlight;

    public BookingService(DAOBookingFileText daoBooking, DAOFlightFileText daoFlight) {
        this.daoBooking = daoBooking;
        this.daoFlight = daoFlight;
    }

    public void book(int flightId, List<Passenger> passengers) {
        daoBooking.create(new Booking(flightId, passengers));
        Optional<Flight> flightExtra = daoFlight.get(flightId);
        daoFlight.delete(flightId);
        flightExtra.ifPresent(f -> f.setFreeSpaces(f.getFreeSpaces() - passengers.size()));
        daoFlight.create(flightExtra.orElseThrow(RuntimeException::new));
    }

    public String cancelBooking(int bookingId) {
        return daoBooking.get(bookingId).map(b -> {
            Flight newFlight = daoFlight.get(b.getFlight_id()).orElseThrow(RuntimeException::new);
            newFlight.setFreeSpaces(newFlight.getFreeSpaces() + b.getPassengers().size());
            daoBooking.delete(bookingId);
            daoFlight.delete(b.getFlight_id());
            daoFlight.create(newFlight);
            return "Booking deleted.";
        }).orElse("There is no any booking.");
    }

    public List<String> getMyFlights(String name, String surname) {
        return daoBooking.getAllBy(Predicates.isMyFlight(name, surname))
                .stream().map(Booking::represent).collect(Collectors.toList());
    }
}

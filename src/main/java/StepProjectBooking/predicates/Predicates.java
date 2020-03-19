package StepProjectBooking.predicates;

import StepProjectBooking.entity.Booking;
import StepProjectBooking.entity.Flight;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Predicates {
    public static Predicate<Flight> isSomeHoursBefore(int hours) {
        return f -> f.getDate().isBefore(LocalDate.now().plusDays(hours% 24 !=0 ? (hours/24+1): hours/24));
    }

    public static Predicate<Flight> isBookable(String dest, LocalDate date, int numOfPeople) {
        return f -> f.getFreeSpaces() >= numOfPeople &&
                f.getDest().toLowerCase().equals(dest) && f.getDate().equals(date);
    }

    public static Predicate<Booking> isMyFlight(String name, String surname) {
        return b -> b.getPassengers().stream().anyMatch(p ->
                p.getName().toLowerCase().trim().equals(name) &&
                        p.getSurname().toLowerCase().trim().equals(surname));
    }
}

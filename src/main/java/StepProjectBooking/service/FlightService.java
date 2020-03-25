package StepProjectBooking.service;

import StepProjectBooking.database.DAOFlightFileText;
import StepProjectBooking.entity.Flight;
import StepProjectBooking.predicates.Predicates;
import StepProjectBooking.randoms.FlightGenerator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    DAOFlightFileText daoFlight;

    public FlightService(DAOFlightFileText daoFlight) {
        this.daoFlight = daoFlight;
    }

    public List<String> getAllFlights() {
        return daoFlight.getAllBy(Predicates.isSomeHoursBefore())
                .stream().map(Flight::represent).collect(Collectors.toList());
    }

    public String getFlightById(int flightId) {
        return daoFlight.get(flightId).map(Flight::represent)
                .orElse("No flight found");
    }

    public List<String> searchForBook(String dest, LocalDate date, int numOfPeople) {
        return daoFlight.getAllBy(Predicates.isBookable(dest, date, numOfPeople))
                .stream().map(Flight::represent).collect(Collectors.toList());
    }

    public Collection<Flight> getAll() {
        return daoFlight.getAll();
    }

    public void addFlight(Flight flight) {
        //daoFlight.create(FlightGenerator.genFlight());
        daoFlight.create(flight);
    }

    public String showMenu() {
        StringBuilder sb = new StringBuilder();
        return sb.append("1. Online - board\n")
                .append("2. Show the flight info\n")
                .append("3. Search and book a flight\n")
                .append("4. Cancel the booking\n")
                .append("5. My flights\n")
                .append("6. Exit\n")
                .append("7. End session")
                .toString();
    }

    public String addFlight1(Flight flight) {
        daoFlight.create(flight);
        return flight.represent2();
    }
}

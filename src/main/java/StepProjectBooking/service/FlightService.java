package StepProjectBooking.service;

import StepProjectBooking.database.DAOBookingFileText;
import StepProjectBooking.database.DAOFlightFileText;
import StepProjectBooking.entity.Flight;
import StepProjectBooking.predicates.Predicates;
import StepProjectBooking.randoms.FlightGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    DAOBookingFileText daoBooking;
    DAOFlightFileText daoFlight;

    public FlightService(DAOBookingFileText daoBooking, DAOFlightFileText daoFlight) {
        this.daoBooking = daoBooking;
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

    public boolean getAll() {
        return daoFlight.getAll().size() == 0;
    }

    public void addFlight() {
        daoFlight.create(FlightGenerator.genFlight());
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
}
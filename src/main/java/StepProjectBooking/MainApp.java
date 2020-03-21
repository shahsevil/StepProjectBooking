/*------------------------------------------------------------------------------
This App doesn't work because of some changes on Service and Controller classes
-------------------------------------------------------------------------------*/


//package StepProjectBooking;
//
//import StepProjectBooking.database.DAOBookingFileText;
//import StepProjectBooking.database.DAOFlightFileText;
//import StepProjectBooking.controller.Controller;
//import StepProjectBooking.exceptions.FlightNotCancelledException;
//import StepProjectBooking.exceptions.FlightNotFoundException;
//import StepProjectBooking.exceptions.UserFlightsNotFound;
//import StepProjectBooking.io.ConsoleMain;
//import StepProjectBooking.service.Service;
//import StepProjectBooking.entity.User;
//import StepProjectBooking.controller.UserController;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//
//public class MainApp {
//    public static void main(String[] args) throws IOException {
//
//        ConsoleMain console = new ConsoleMain();
//        DAOBookingFileText daoBooking = new DAOBookingFileText("booking.txt");
//        DAOFlightFileText daoFlight = new DAOFlightFileText("flight.txt");
//        Service service = new Service(daoBooking, daoFlight);
//        Controller controller = new Controller(console, service);
//
//        if (controller.getAll()) {
//            int i = 0;
//            while (i++ < 100) {
//                controller.addFlight();
//            }
//        }
//
//        login();
//
//        boolean cont = true;
//        while (cont) {
//            console.printLn(controller.showMenu());
//            console.print("Enter menu num: \n");
//            String input = console.readLn();
//            switch (input) {
//                case "1":
//                    console.printLn(controller.show());
//                    break;
//                case "2":
//                    try {
//                        console.printLn(controller.search());
//                    } catch (FlightNotFoundException | NumberFormatException ex) {
//                        console.printLn("No flight found...");
//                    }
//                    break;
//                case "3":
//                    console.printLn("Enter destination: ");
//                    String dest = console.readLn().toLowerCase().trim();
//                    LocalDate date = null;
//                    try {
//                        console.printLn("Enter date (YYYY-MM-DD): ");
//                        date = LocalDate.parse(console.readLn());
//                    } catch (DateTimeParseException ex) {
//                        console.printLn("Be sure you entering valid date...");
//                        console.printLn(controller.showMenu());
//                        break;
//                    }
//                    int numOfPeople;
//                    try {
//                        console.printLn("Enter number of people: ");
//                        numOfPeople = Integer.parseInt(console.readLn());
//                    } catch (NumberFormatException ex) {
//                        console.printLn("Invalid input...");
//                        break;
//                    }
//                    console.print("\n");
//                    if (controller.searchForBook(dest, date, numOfPeople).length() == 0) {
//                        console.printLn("There is no flight with given conditions");
//                        break;
//                    } else {
//                        console.printLn(controller.searchForBook(dest, date, numOfPeople));
//                        console.printLn("Enter flightId to book or 0 to exit: ");
//                        String flightIdOrExit = console.readLn();
//                        if (flightIdOrExit.equals("0")) break;
//                        else {
//                            controller.book(flightIdOrExit, numOfPeople);
//                            break;
//                        }
//                    }
//                case "4":
//                    try {
//                        console.print("Enter booking id: ");
//                        int id = Integer.parseInt(console.readLn());
//                        console.printLn(controller.cancelBooking(id));
//                    } catch (FlightNotCancelledException | NumberFormatException ex) {
//                        console.printLn("Something went wrong when cancelling flight...");
//                    }
//                    break;
//                case "5":
//                    try {
//                        console.printLn(controller.getMyFlights());
//                    } catch (UserFlightsNotFound ex) {
//                        console.printLn("You have no flight...");
//                    }
//                    break;
//                case "6":
//                    cont = false;
//                    console.printLn("Bye...");
//                    break;
//                case "7":
//                    logout();
//                    break;
//                default:
//                    console.printLn("Please enter menu num again");
//            }
//        }
//    }
//
//    private static void logout() throws IOException {
//        ConsoleMain consoleMain = new ConsoleMain();
//        consoleMain.printLn("You logged out...");
//        login();
//    }
//
//    private static void forRegistering() throws IOException {
//        UserController userController = new UserController();
//        ConsoleMain consoleMain = new ConsoleMain();
//        BufferedReader read = new BufferedReader(new FileReader("users.txt"));
//        consoleMain.print("Enter following information about you: your username and your password");
//        String userName = consoleMain.readLn();
//        String password = consoleMain.readLn();
//        try {
//            while (read.readLine() != null) {
//            }
//            userController.userByReg(userName, password);
//        } catch (Exception ex) {
//            consoleMain.printLn("Wrong name or password");
//            forRegistering();
//        }
//        read.close();
//    }
//
//    private static void newUserRegistrator() throws IOException {
//        UserController userController = new UserController();
//        ConsoleMain consoleMain = new ConsoleMain();
//        FileWriter fileWriter = new FileWriter("users.txt", true);
//        consoleMain.printLn("Please, enter your information.\nEnter your name:");
//        String name1 = consoleMain.readLn();
//        consoleMain.printLn("Enter your password:");
//        String password1 = consoleMain.readLn();
//        try {
//            fileWriter.write(System.lineSeparator());
//            fileWriter.write(name1);
//            fileWriter.write(System.lineSeparator());
//            fileWriter.write(password1);
//            consoleMain.printLn("New user saved...");
//            fileWriter.close();
//        } catch (IOException ex) {
//            throw new RuntimeException("Something went wrong/..", ex);
//        }
//        userController.save(new User(name1, password1));
//    }
//
//    public static void login() throws IOException {
//        ConsoleMain consoleMain = new ConsoleMain();
//        consoleMain.print("Do you have an account?Yes or No?");
//        switch (consoleMain.readLn().toLowerCase().trim()) {
//            case "yes":
//                forRegistering();
//                break;
//            case "no":
//                newUserRegistrator();
//                break;
//            default:
//                throw new IllegalArgumentException("----Yes---No----");
//        }
//    }
//}

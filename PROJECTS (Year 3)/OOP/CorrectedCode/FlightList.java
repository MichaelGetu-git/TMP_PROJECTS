package CorrectedCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;

class FlightList {
    private static List<Flight> flights = new ArrayList<>();
    private static List<Seat> seats = new ArrayList<>();
    
    private static ListProperty<Flight> flightsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
public static void addFlight(List<FlightRoute> availableRoutes, String type, double weight,
                             int capacity, String status, double fuelConsumption,
                             String reservationInfo, Double ticketCost) {
    // Display available routes to the user
    RouteList.displayRoutes(availableRoutes);

    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Add Flight");
    dialog.setHeaderText(null);
    dialog.setContentText("Enter selected route ID:");

    Optional<String> result = dialog.showAndWait();

    result.ifPresent(routeIdStr -> {
        int selectedRouteId = Integer.parseInt(routeIdStr);
        FlightRoute selectedRoute = RouteList.findRouteById(availableRoutes, selectedRouteId);

        if (selectedRoute != null) {
            Flight newFlight = new Flight(selectedRoute, type, weight, capacity, status,
                    fuelConsumption, reservationInfo, ticketCost);
            flights.add(newFlight);
            flightsProperty.set(FXCollections.observableArrayList(flights));
        }
    });
}


    static ObservableList<Flight> getFlightObservableList() {
        return FXCollections.observableArrayList(flights);
    }

   public static void searchFlightById(int flightId) {
    for (Flight flight : flights) {
        if (flight.getFlightId() == flightId) {
            System.out.println("Flight details:\n" + flight);
            return;
        }
    }
    System.out.println("Flight not found with ID: " + flightId);
}

    public static void displayFlights() {
        System.out.println("All Flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public static boolean deleteFlight(int flightId) {
        Iterator<Flight> iterator = flights.iterator();
        while (iterator.hasNext()) {
            Flight flight = iterator.next();
            if (flight.getFlightId() == flightId) {
                iterator.remove();
                return true; // Deletion successful
            }
        }
        return false; // Flight not found
    }

   private static void displayRoutes(List<FlightRoute> routes) {
    System.out.println("Available Routes:");
    for (FlightRoute route : routes) {
        System.out.println(route); // Assuming FlightRoute has a meaningful toString() method
    }
}

    public static FlightRoute findRouteById(List<FlightRoute> routes, int routeId) {
        for (FlightRoute route : routes) {
            if (route.getRouteId() == routeId) {
                return route;
            }
        }
        return null;
    }

    public static Flight findFlightById(int flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId() == flightId) {
                return flight;
            }
        }
        return null;
    }

  public static void displayAvailableSeats(Flight flight) {
    System.out.println("Available Seats for Flight " + flight.getFlightId() + ":");
    for (Seat seat : flight.getSeats()) {
        System.out.println(seat); // Assuming Seat has a meaningful toString() method
    }
}

public static boolean isSeatFree(Flight flight, String seatClass) {
    for (Seat seat : flight.getSeats()) {
        if (seat.getSeatClass().equalsIgnoreCase(seatClass) && seat.getAvailableSeats() > 0) {
            return true;
        }
    }
    return false;
}

public static Seat findSeatByClass(Flight flight, String seatClass) {
    for (Seat seat : flight.getSeats()) {
        if (seat.getSeatClass().equalsIgnoreCase(seatClass) && seat.getAvailableSeats() > 0) {
            return seat;
        }
    }
    return null;
}

public static boolean bookSeat(Flight flight, String seatClass) {
    Seat seat = findSeatByClass(flight, seatClass);
    if (seat != null && seat.getAvailableSeats() > 0) {
        seat.setAvailableSeats(seat.getAvailableSeats() - 1);
        flight.addBookedSeat(seat);
        
        
         // Update ticket cost based on the class of the booked seat
        
        return true;
    }
    return false;
}

    public static boolean cancelSeat(Flight flight, String seatClass) {
    Seat seat = findSeatByClass(flight, seatClass);
    if (seat != null && seat.getAvailableSeats() < seat.getTotalSeats()) {
        seat.setAvailableSeats(seat.getAvailableSeats() + 1);
        flight.removeBookedSeat(seat);  // Remove the canceled seat from the booked seats
        return true;
    }
    return false;
}

    
    public static ObservableList<Flight> getAllFlightsObservableList() {
    return FXCollections.observableArrayList(flights);
}

    
    public static List<Flight> getAllFlights() {
    return flights;
}
     // Getter for flightsProperty
    public static ListProperty<Flight> flightsProperty() {
        return flightsProperty;
    }
    
    public static void displayFlightDetailsWithSeats(int flightId) {
    Flight flight = findFlightById(flightId);

    if (flight != null) {
        System.out.println("Flight details for Flight " + flightId + ":");
        System.out.println(flight);

        // Display available seats for the flight
        displayAvailableSeats(flight);
    } else {
        System.out.println("Flight not found with ID: " + flightId);
    }
}


}

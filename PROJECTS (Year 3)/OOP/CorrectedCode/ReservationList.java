package CorrectedCode;

import java.util.ArrayList;
import java.util.List;

public class ReservationList {
    private static List<Reservation> reservations = new ArrayList<>();

    public static boolean makeReservation(String customerUserID, FlightRoute route, int flightId, String seatClass) {
        // Check if the customer with the given userID exists
        Customer customer = CustomerList.searchCustomer("userID", customerUserID);
        if (customer == null) {
            System.out.println("Customer not found with userID: " + customerUserID + ". Cannot make a reservation.");
            return false;
        }

        // Check if the flight with the given ID exists
        Flight flight = FlightList.findFlightById(flightId);
        if (flight == null) {
            System.out.println("Flight not found with ID: " + flightId + ". Cannot make a reservation.");
            return false;
        }

       // Check if the flight is on the specified route
        if (!flight.getFrom().equals(route.getFrom()) || !flight.getTo().equals(route.getTo())) {
            System.out.println("Flight is not on the specified route. Cannot make a reservation.");
            return false;
        }


        // Check if the seat is free and book the seat
        if (FlightList.isSeatFree(flight, seatClass)) {
            boolean booked = FlightList.bookSeat(flight, seatClass);

            if (booked) {
                // Create a reservation and add it to the list
                Seat reservedSeat = FlightList.findSeatByClass(flight, seatClass);
                Reservation reservation = new Reservation(customer, flight, reservedSeat);
                reservations.add(reservation);
                System.out.println("Reservation successful:\n" + reservation);
                return true;
            } else {
                System.out.println("Failed to book the seat. Cannot make a reservation.");
                return false;
            }
        } else {
            System.out.println("The selected seat is not available. Cannot make a reservation.");
            return false;
        }
    }


    public static void displayReservations() {
        System.out.println("All Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
    
    public static boolean cancelReservation(String customerUserID) {
    // Check if the customer with the given userID exists
    Customer customer = CustomerList.searchCustomer("userID", customerUserID);
    if (customer == null) {
        System.out.println("Customer not found with userID: " + customerUserID + ". Cannot cancel reservations.");
        return false;
    }

    // Find and cancel all reservations for the specified customer
    List<Reservation> reservationsToRemove = findReservationsByCustomer(customer);
    if (!reservationsToRemove.isEmpty()) {
        for (Reservation reservation : reservationsToRemove) {
            // Cancel the reservation and free up the booked seat
            Flight flight = reservation.getFlight();
            Seat reservedSeat = reservation.getReservedSeat();
            FlightList.cancelSeat(flight, reservedSeat.getSeatClass());
            reservations.remove(reservation);
        }
        System.out.println("Reservations canceled successfully for customer: " + customerUserID);
        return true;
    } else {
        System.out.println("No reservations found for customer: " + customerUserID);
        return false;
    }
}

private static List<Reservation> findReservationsByCustomer(Customer customer) {
    List<Reservation> customerReservations = new ArrayList<>();
    for (Reservation reservation : reservations) {
        if (reservation.getCustomer().equals(customer)) {
            customerReservations.add(reservation);
        }
    }
    return customerReservations;
}


}

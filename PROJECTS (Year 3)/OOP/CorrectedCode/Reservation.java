package CorrectedCode;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static List<Reservation> reservations = new ArrayList<>();
    
    private int reservationId;
    private Customer customer;
    private Flight flight;
    private Seat reservedSeat;

    // Constructors
    public Reservation(Customer customer, Flight flight, Seat reservedSeat) {
        this.customer = customer;
        this.flight = flight;
        this.reservedSeat = reservedSeat;
        reservations.add(this);
    }

    
    public int getReservationId() {
        return reservationId;
    }

    // Setters and getters for each attribute
    public Customer getCustomer() {
        return customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public Seat getReservedSeat() {
        return reservedSeat;
    }

    // Static method to get all reservations
    public static List<Reservation> getAllReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer.getName() +
                ", flight=" + flight.getFlightId() +
                ", reservedSeat=" + reservedSeat +
                '}';
    }
}

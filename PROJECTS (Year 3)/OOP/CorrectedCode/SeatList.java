/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CorrectedCode;


/**
 *
 * @author HP 15
 */
public class SeatList {
    
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

}

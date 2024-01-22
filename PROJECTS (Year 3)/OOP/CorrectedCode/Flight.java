/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CorrectedCode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP 15
 */

class Flight extends FlightRoute {
    private static int flightCounter = 1;
    private int flightId;
    private String flightType;
    private double flightWeight;
    private int flightCapacity;
    private String flightStatus;
    private double flightFuelConsumption;
    private String flightReservationInfo;
    private Double ticketCost;
    private List<Seat> seats;
    private List<Seat> bookedSeats;



    // Constructors
     public Flight(FlightRoute defaultRoute, String flightType, double flightWeight,
                  int flightCapacity, String flightStatus, double flightFuelConsumption,
                  String flightReservationInfo, Double ticketCost) {
        super(defaultRoute.getFrom(), defaultRoute.getTo(), defaultRoute.getFlightDistance(),
              defaultRoute.getFlightTime(), defaultRoute.getFlightPath(), defaultRoute.getFlightSchedule());
        this.flightId = flightCounter++;
        this.flightType = flightType;
        this.flightWeight = flightWeight;
        this.flightCapacity = flightCapacity;
        this.flightStatus = flightStatus;
        this.flightFuelConsumption = flightFuelConsumption;
        this.flightReservationInfo = flightReservationInfo;
        this.ticketCost = ticketCost;
        this.seats = initializeSeats(); // Initialize seats when creating a new Flight
        this.bookedSeats = new ArrayList<>();
   
     }
     
     
     // Method to initialize seats
private List<Seat> initializeSeats() {
    List<Seat> seatList = new ArrayList<>();

    // Calculate the number of seats based on percentages
    int totalCapacity = getFlightCapacity();
    int economySeats = (int) (totalCapacity * 0.7);
    int businessSeats = (int) (totalCapacity * 0.2);
    int firstClassSeats = (int) (totalCapacity * 0.1);

    // Add seats with their properties (number of seats, available seats, seat cost, seat class)
    seatList.add(new Seat("Economy", economySeats, economySeats, 500.0));       // Example for Economy class
    seatList.add(new Seat("Business", businessSeats, businessSeats, 1000.0));    // Example for Business class
    seatList.add(new Seat("First Class", firstClassSeats, firstClassSeats, 1500.0)); // Example for First Class

    return seatList;
}

     
     
    public void performBooking(Seat selectedSeat) {
        // Update seat status
        selectedSeat.setAvailableSeats(selectedSeat.getAvailableSeats() - 1);
    }

     @Override
    public String toString() {
        return "Flight ID: " + flightId +
               ", Type: " + flightType +
               ", Weight: " + flightWeight +
               ", Capacity: " + flightCapacity +
               ", Status: " + flightStatus +
               ", Fuel Consumption: " + flightFuelConsumption +
               ", Reservation Info: " + flightReservationInfo +
               ", Ticket Cost: " + ticketCost;
               
    }

    // Setters and getters for each attribute
    public int getFlightId() {
        return flightId;
    }

    public String getFlightType() {
        return flightType;
    }

    public double getFlightWeight() {
        return flightWeight;
    }

    public int getFlightCapacity() {
        return flightCapacity;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public double getFlightFuelConsumption() {
        return flightFuelConsumption;
    }

    public String getFlightReservationInfo() {
        return flightReservationInfo;
    }

    public Double getTicketCost() {
        return ticketCost;
    }
      public void addBookedSeat(Seat seat) {
        bookedSeats.add(seat);
    }

    public void removeBookedSeat(Seat seat) {
        bookedSeats.remove(seat);
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public void setTicketCost(Double ticketCost) {
        this.ticketCost = ticketCost;
    }
    
     public String getType() {
        return this.flightType;
    }


}

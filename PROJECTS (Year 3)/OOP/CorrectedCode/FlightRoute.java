/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CorrectedCode;


/**
 *
 * @author HP 15
 */
/**
 * Represents a flight route in the reservation system.
 */
class FlightRoute {
    private static int routeCounter = 1;

    private int routeId;
    private String from;
    private String to;
    private double flightDistance;
    private double flightTime;
    private String flightPath;
    private String flightSchedule;

    // Constructors
    public FlightRoute(String from, String to, double flightDistance, double flightTime,
                       String flightPath, String flightSchedule) {
        this.routeId = routeCounter++;
        this.from = from;
        this.to = to;
        this.flightDistance = flightDistance;
        this.flightTime = flightTime;
        this.flightPath = flightPath;
        this.flightSchedule = flightSchedule;
    }

    // Setters and getters for each attribute
    public int getRouteId() {
        return routeId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getFlightDistance() {
        return flightDistance;
    }

    public double getFlightTime() {
        return flightTime;
    }

    public String getFlightPath() {
        return flightPath;
    }

    public String getFlightSchedule() {
        return flightSchedule;
    }
    
     public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFlightDistance(double flightDistance) {
        this.flightDistance = flightDistance;
    }

    public void setFlightTime(double flightTime) {
        this.flightTime = flightTime;
    }

    public void setFlightPath(String flightPath) {
        this.flightPath = flightPath;
    }

    public void setFlightSchedule(String flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    
    @Override
    public String toString() {
        return "FlightRoute{" +
                "source='" + from + '\'' +
                ", destination='" + to + '\'' +
                ", distance=" + flightDistance +
                ", duration=" + flightTime +
                ", path='" + flightPath + '\'' +
                ", schedule='" + flightSchedule + '\'' +
                '}';
    }
}

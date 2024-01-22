package CorrectedCode;


class Seat {
    private String seatClass;
    private int totalSeats;
    private int availableSeats;
    private double seatCost;

    // Constructor
    public Seat(String seatClass, int totalSeats, int availableSeats, double seatCost) {
        this.seatClass = seatClass;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.seatCost = seatCost;
    }

    // Setters and getters for each attribute
    public String getSeatClass() {
        return seatClass;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getSeatCost() {
        return seatCost;
    }
    
    public void setSeatCost(Double seatCost) {
        this.seatCost = seatCost;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatClass='" + seatClass + '\'' +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", seatCost=" + seatCost +
                '}';
    }
}
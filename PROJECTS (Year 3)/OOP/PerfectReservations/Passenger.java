/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PerfectReservations;

/**
 *
 * @author HP 15
 */
public class Passenger {
    private String passportNo;
    private String pnrNo;
    private String address;
    private String nationality;
    private String name;
    private String gender;
    private String phNo;
    private String flightCode;

    // Constructors
    public Passenger() {
        // Default constructor
    }

    public Passenger(String passportNo, String pnrNo, String address, String nationality,
                     String name, String gender, String phNo, String flightCode) {
        this.passportNo = passportNo;
        this.pnrNo = pnrNo;
        this.address = address;
        this.nationality = nationality;
        this.name = name;
        this.gender = gender;
        this.phNo = phNo;
        this.flightCode = flightCode;
    }

    // Getters and setters
    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    // Additional methods if needed...

    @Override
    public String toString() {
        return "Passenger{" +
                "passportNo='" + passportNo + '\'' +
                ", pnrNo='" + pnrNo + '\'' +
                ", address='" + address + '\'' +
                ", nationality='" + nationality + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phNo='" + phNo + '\'' +
                ", flightCode='" + flightCode + '\'' +
                '}';
    }
}

package PerfectReservations;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ticket Reservation");

        Label reservationLabel = new Label("TICKET RESERVATION");
        reservationLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold");

        Label pnrLabel = new Label("PNR Number:");
        TextField pnrField = new TextField();

        Label ticketIdLabel = new Label("Ticket ID:");
        TextField ticketIdField = new TextField();

        Label flightCodeLabel = new Label("Flight Code:");
        ComboBox<String> flightCodeComboBox = new ComboBox<>();
        flightCodeComboBox.getItems().addAll("f1005", "f1006", "f1007"); // Add your flight codes here

        Label journeyDateLabel = new Label("Journey Date:");
        DatePicker journeyDatePicker = new DatePicker();

        Label journeyTimeLabel = new Label("Journey Time:");
        TextField journeyTimeField = new TextField();

        Label sourceLabel = new Label("Source:");
        TextField sourceField = new TextField();

        Label destinationLabel = new Label("Destination:");
        TextField destinationField = new TextField();

        Label seatClassLabel = new Label("Seat Class:");
        ComboBox<String> seatClassComboBox = new ComboBox<>();
        seatClassComboBox.getItems().addAll("A", "B", "C"); // Add your seat class codes here

        Button reserveButton = new Button("RESERVE");
        reserveButton.setOnAction(e -> reserveTicket(
                pnrField.getText(),
                ticketIdField.getText(),
                flightCodeComboBox.getValue(),
                journeyDatePicker.getValue().toString(),
                journeyTimeField.getText(),
                sourceField.getText(),
                destinationField.getText(),
                seatClassComboBox.getValue()
        ));

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.getChildren().addAll(
                reservationLabel,
                pnrLabel, pnrField,
                ticketIdLabel, ticketIdField,
                flightCodeLabel, flightCodeComboBox,
                journeyDateLabel, journeyDatePicker,
                journeyTimeLabel, journeyTimeField,
                sourceLabel, sourceField,
                destinationLabel, destinationField,
                seatClassLabel, seatClassComboBox,
                reserveButton
        );

        Scene scene = new Scene(vBox, 400, 550);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void reserveTicket(String pnr, String ticketId, String flightCode, String journeyDate,
                               String journeyTime, String source, String destination, String seatClass) {
        try {
            // JDBC Connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "root", "");

            // SQL Query to insert data into 'reservation' table
            String reservationQuery = "INSERT INTO reservation (pnr_no, ticket_id, f_code, jny_date, jny_time, src, dst, seat_class) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement reservationStatement = connection.prepareStatement(reservationQuery);

            reservationStatement.setString(1, pnr);
            reservationStatement.setString(2, ticketId);
            reservationStatement.setString(3, flightCode);
            reservationStatement.setString(4, journeyDate);
            reservationStatement.setString(5, journeyTime);
            reservationStatement.setString(6, source);
            reservationStatement.setString(7, destination);
            reservationStatement.setString(8, seatClass);

            // Execute the reservation query
            reservationStatement.executeUpdate();

            // Update the capacity for the selected seat class
            String updateCapacityQuery = "UPDATE sector SET capacity = capacity - 1 WHERE flight_code = ? AND class_code = ?";
            PreparedStatement updateCapacityStatement = connection.prepareStatement(updateCapacityQuery);
            updateCapacityStatement.setString(1, flightCode);
            updateCapacityStatement.setString(2, seatClass);

            // Execute the capacity update query
            updateCapacityStatement.executeUpdate();

            // Close the resources
            updateCapacityStatement.close();
            reservationStatement.close();
            connection.close();

            System.out.println("Ticket reserved and saved to the database. Flight capacity updated for seat class: " + seatClass);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

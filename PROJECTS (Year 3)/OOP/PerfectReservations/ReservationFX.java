/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PerfectReservations;

/**
 *
 * @author HP 15
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

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

        Button reserveButton = new Button("RESERVE");
        reserveButton.setOnAction(e -> reserveTicket(
                pnrField.getText(),
                ticketIdField.getText(),
                flightCodeComboBox.getValue(),
                journeyDatePicker.getValue().toString(),
                journeyTimeField.getText(),
                sourceField.getText(),
                destinationField.getText()
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
                reserveButton
        );

        Scene scene = new Scene(vBox, 400, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void reserveTicket(String pnr, String ticketId, String flightCode, String journeyDate,
                           String journeyTime, String source, String destination) {
    try {
        // JDBC Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "root", "");

        // SQL Query to insert data into 'reservation' table
        String query = "INSERT INTO reservation (pnr_no, ticket_id, f_code, jny_date, jny_time, src, dst) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, pnr);
        preparedStatement.setString(2, ticketId);
        preparedStatement.setString(3, flightCode);
        preparedStatement.setString(4, journeyDate);
        preparedStatement.setString(5, journeyTime);
        preparedStatement.setString(6, source);
        preparedStatement.setString(7, destination);

        // Execute the query
        preparedStatement.executeUpdate();

        // Close the resources
        preparedStatement.close();
        connection.close();

        System.out.println("Ticket reserved and saved to the database!");
    } catch (SQLException exception) {
        exception.printStackTrace();
    }
}
}


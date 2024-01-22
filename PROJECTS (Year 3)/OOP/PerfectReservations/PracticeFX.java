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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PracticeFX extends Application {

    static Connection mycon = null;
    static String url = "jdbc:mysql://localhost:3306";
    static String dbname = "/airline";
    static String driver = "com.mysql.jdbc.Driver";
    static String userName = "root";
    static String password = "";
    Connection con = null;

    PreparedStatement pst = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CANCELLATION");

        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #ff99cc;"); // Set background color
        root.setPadding(new Insets(20));

        Label lblCancellation = new Label("CANCELLATION");
        lblCancellation.setStyle("-fx-font-size: 31;");
        TextField textField = new TextField();
        textField.setPromptText("PASSENGER NO");

        TextField textField_1 = new TextField();
        textField_1.setPromptText("CANCELLATION NO");

        TextField textField_2 = new TextField();
        textField_2.setPromptText("CANCELLATION DATE");

        TextField textField_3 = new TextField();
        textField_3.setPromptText("TICKET ID");

        TextField textField_4 = new TextField();
        textField_4.setPromptText("FLIGHT CODE");

        Button btnCancel = new Button("CANCEL");
        btnCancel.setStyle("-fx-font-size: 14;");
        btnCancel.setOnAction(e -> handleCancellation(textField.getText(), textField_1.getText(),
                textField_2.getText(), textField_3.getText(), textField_4.getText()));

        root.getChildren().addAll(lblCancellation, textField, textField_1, textField_2, textField_3, textField_4, btnCancel);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void handleCancellation(String passengerNo, String cancellationNo, String cancellationDate,
                                    String ticketId, String flightCode) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + dbname, userName, password);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }

        try {
            pst = con.prepareStatement("INSERT INTO `airline`.`cancellation` (`pnr_no`, `cancellation_no`, `cancellation_date`, `ticket_id`, `fli_code`) VALUES (?, ?, ?, ?, ?);");
            pst.setString(1, passengerNo);
            pst.setString(2, cancellationNo);
            pst.setString(3, cancellationDate);
            pst.setString(4, ticketId);
            pst.setString(5, flightCode);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class LoginFX extends Application {

    private TextField t1, t2;
    private Label l1, l2;
    private Button b2, b3, b4;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        l1 = new Label("Username");
        l1.setStyle("-fx-font-weight: bold;");

        l2 = new Label("Password");
        l2.setStyle("-fx-font-weight: bold;");

        t1 = new TextField();
        t2 = new PasswordField();

        b2 = new Button("Reset");
        b2.setStyle("-fx-font-weight: bold;");

        b3 = new Button("Submit");
        b3.setStyle("-fx-font-weight: bold;");

        b4 = new Button("Close");
        b4.setStyle("-fx-font-weight: bold;");

        grid.add(l1, 0, 0);
        grid.add(t1, 1, 0);
        grid.add(l2, 0, 1);
        grid.add(t2, 1, 1);
        grid.add(b2, 0, 3);
        grid.add(b3, 1, 3);
        grid.add(b4, 2, 3);

        b2.setOnAction(e -> handleResetButton());
        b3.setOnAction(e -> handleSubmitButton());
        b4.setOnAction(e -> handleCloseButton());

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleResetButton() {
        t1.setText("");
        t2.setText("");
    }

    private void handleSubmitButton() {
        try {
             conn c1 = new conn();
            String s1 = t1.getText();
            String s2 = t2.getText();
            
                String str = "select * from login where username = '"+s1+"' and password = '"+s2+"'";
                ResultSet rs = c1.s.executeQuery(str);  
                
            if (rs.next()) {
                new MainframeFX();
                closeStage();
            } else {
                showErrorAlert("Invalid Login");
            }

            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCloseButton() {
        System.exit(0);
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeStage() {
        ((Stage) b3.getScene().getWindow()).close();
    }
}


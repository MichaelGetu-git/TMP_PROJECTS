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

public class CancelFX extends Application {

    private TextField textField, textField_1, textField_2, textField_3, textField_4;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CANCELLATION");

        GridPane gridPane = createCancelFormPane();
        addUIControls(gridPane);

        Scene scene = new Scene(gridPane, 860, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createCancelFormPane() {
        GridPane gridPane = new GridPane(); 
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        Label cancellationLabel = new Label("CANCELLATION");
        cancellationLabel.setStyle("-fx-font-size: 31");
        gridPane.add(cancellationLabel, 0, 0, 2, 1);

        Label passengerNoLabel = new Label("PASSENGER NO");
        passengerNoLabel.setStyle("-fx-font-size: 17");
        gridPane.add(passengerNoLabel, 0, 1);

        Label cancellationNoLabel = new Label("CANCELLATION NO");
        cancellationNoLabel.setStyle("-fx-font-size: 17");
        gridPane.add(cancellationNoLabel, 0, 2);

        Label cancellationDateLabel = new Label("CANCELLATION DATE");
        cancellationDateLabel.setStyle("-fx-font-size: 17");
        gridPane.add(cancellationDateLabel, 0, 3);

        Label ticketIdLabel = new Label("TICKET_ID");
        ticketIdLabel.setStyle("-fx-font-size: 17");
        gridPane.add(ticketIdLabel, 0, 4);

        Label flightCodeLabel = new Label("FLIGHT_CODE");
        flightCodeLabel.setStyle("-fx-font-size: 17");
        gridPane.add(flightCodeLabel, 0, 5);

        Button cancelButton = new Button("CANCEL");
        cancelButton.setStyle("-fx-font-size: 14");
        cancelButton.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        cancelButton.setTextFill(javafx.scene.paint.Color.WHITE);
        gridPane.add(cancelButton, 1, 6);

        textField = new TextField();
        gridPane.add(textField, 1, 1);

        textField_1 = new TextField();
        gridPane.add(textField_1, 1, 2);

        textField_2 = new TextField();
        gridPane.add(textField_2, 1, 3);

        textField_3 = new TextField();
        gridPane.add(textField_3, 1, 4);

        textField_4 = new TextField();
        gridPane.add(textField_4, 1, 5);

        cancelButton.setOnAction(event -> handleCancelButton());

    }

    private void handleCancelButton() {
    String passengerNo = textField.getText();
    String cancellationNo = textField_1.getText();
    String cancellationDate = textField_2.getText();
    String ticketId = textField_3.getText();
    String flightCode = textField_4.getText();

    if (passengerNo.isEmpty() || cancellationNo.isEmpty() || cancellationDate.isEmpty() || ticketId.isEmpty() || flightCode.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!", ButtonType.OK);
        alert.showAndWait();
        return;
    }

    try {
        conn c = new conn();
        
          String deleteReservationQuery = "DELETE FROM reservation WHERE pnr_no = '" + passengerNo + "' AND ticket_id = '" + ticketId + "'";
          c.s.executeUpdate(deleteReservationQuery);

        String str = "INSERT INTO cancellation (pnr_no, cancellation_no, cancellation_date, fli_code) VALUES ('" + passengerNo + "', '" + cancellationNo + "', '" + cancellationDate + "', '" + flightCode + "')";

        c.s.executeUpdate(str);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ticket Canceled", ButtonType.OK);
        alert.showAndWait();

        Stage stage = (Stage) textField.getScene().getWindow();
        stage.close();
    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred!", ButtonType.OK);
        alert.showAndWait();
    }
}
}

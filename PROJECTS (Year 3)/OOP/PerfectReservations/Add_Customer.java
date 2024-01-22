/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PerfectReservations;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Add_Customer extends Application {

    private TextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6;
    private RadioButton maleRadioButton, femaleRadioButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ADD CUSTOMER DETAILS");

        GridPane gridPane = createFormPane();
        addUIControls(gridPane);

        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        Label passportLabel = new Label("PASSPORT NO");
        gridPane.add(passportLabel, 0, 0);

        textField = new TextField();
        gridPane.add(textField, 1, 0);

        Label pnrLabel = new Label("PNR NO");
        gridPane.add(pnrLabel, 0, 1);

        textField_1 = new TextField();
        gridPane.add(textField_1, 1, 1);

        Label addressLabel = new Label("ADDRESS");
        gridPane.add(addressLabel, 0, 2);

        textField_2 = new TextField();
        gridPane.add(textField_2, 1, 2);

        Label nationalityLabel = new Label("NATIONALITY");
        gridPane.add(nationalityLabel, 0, 3);

        textField_3 = new TextField();
        gridPane.add(textField_3, 1, 3);

        Label nameLabel = new Label("NAME");
        gridPane.add(nameLabel, 0, 4);

        textField_4 = new TextField();
        gridPane.add(textField_4, 1, 4);

        Label genderLabel = new Label("GENDER");
        gridPane.add(genderLabel, 0, 5);

        ToggleGroup genderToggleGroup = new ToggleGroup();
        maleRadioButton = new RadioButton("MALE");
        maleRadioButton.setToggleGroup(genderToggleGroup);
        femaleRadioButton = new RadioButton("FEMALE");
        femaleRadioButton.setToggleGroup(genderToggleGroup);

        HBox genderHBox = new HBox(10, maleRadioButton, femaleRadioButton);
        gridPane.add(genderHBox, 1, 5);

        Label phNoLabel = new Label("PH NO");
        gridPane.add(phNoLabel, 0, 6);

        textField_5 = new TextField();
        gridPane.add(textField_5, 1, 6);

        Label flightCodeLabel = new Label("FLIGHT CODE");
        gridPane.add(flightCodeLabel, 0, 7);

        textField_6 = new TextField();
        gridPane.add(textField_6, 1, 7);

        Button saveButton = new Button("SAVE");
        gridPane.add(saveButton, 0, 8, 2, 1);

        saveButton.setOnAction(event -> handleSaveButton());

        Label addPassengersLabel = new Label("ADD CUSTOMER DETAILS");
        addPassengersLabel.setStyle("-fx-font-size: 20");
        gridPane.add(addPassengersLabel, 0, 9, 2, 1);

        gridPane.setAlignment(Pos.CENTER);
    }

    private void handleSaveButton() {
        String passport_No = textField.getText();
        String pnr_no = textField_1.getText();
        String address = textField_2.getText();
        String nationality = textField_3.getText();
        String name = textField_4.getText();
        String fl_code = textField_6.getText();

        String gender = null;
        String ph_no = textField_5.getText();

        if (maleRadioButton.isSelected()) {
            gender = "male";
        } else if (femaleRadioButton.isSelected()) {
            gender = "female";
        }

        try {
            conn c = new conn();
            String str = "INSERT INTO passenger values( '"+pnr_no+"', '"+address+"', '"+nationality+"','"+name+"', '"+gender+"', '"+ph_no+"','"+passport_No+"', '"+fl_code+"')";
                        
            c.s.executeUpdate(str);

            showAlert(Alert.AlertType.INFORMATION, "Customer Added");

            Stage stage = (Stage) textField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

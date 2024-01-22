package PerfectReservations;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FlightInfoFX extends Application {

    private TableView<Flight> table;
    private TextField textField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flight Information");

        Label flightCodeLabel = new Label("FLIGHT CODE");
        flightCodeLabel.setStyle("-fx-font-size: 17");

        Label flightDetailsLabel = new Label("FLIGHT INFORMATION");
        flightDetailsLabel.setStyle("-fx-font-size: 31; -fx-text-fill: #6495ed");

        Button btnShow = new Button("SHOW");
        btnShow.setStyle("-fx-font-size: 20");
        btnShow.setOnAction(e -> showFlightInfo());

        table = new TableView<>();
        table.setEditable(false);

       // Creating table columns
        TableColumn<Flight, String> flightCodeCol = new TableColumn<>("Flight Code");
        flightCodeCol.setCellValueFactory(cellData -> cellData.getValue().flightCodeProperty());

        TableColumn<Flight, String> flightNameCol = new TableColumn<>("Flight Name");
        flightNameCol.setCellValueFactory(cellData -> cellData.getValue().flightNameProperty());

        TableColumn<Flight, String> sourceCol = new TableColumn<>("Source");
        sourceCol.setCellValueFactory(cellData -> cellData.getValue().sourceProperty());

        TableColumn<Flight, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());

        TableColumn<Flight, String> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());

        TableColumn<Flight, String> classCodeCol = new TableColumn<>("Class Code");
        classCodeCol.setCellValueFactory(cellData -> cellData.getValue().classCodeProperty());

        TableColumn<Flight, String> classNameCol = new TableColumn<>("Class Name");
        classNameCol.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());

        table.getColumns().addAll(flightCodeCol, flightNameCol, sourceCol, destinationCol, capacityCol, classCodeCol, classNameCol); // Add other columns similarly...

        textField = new TextField();

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(flightDetailsLabel, flightCodeLabel, btnShow, textField, table);

        Scene scene = new Scene(vBox, 860, 523);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void showFlightInfo() {
    String code = textField.getText();

    try {
        conn c = new conn();
        String str = "select f_code, f_name, src, dst, capacity, class_code, class_name from flight, sector where f_code = '" + code + "'";
        ResultSet rs = c.s.executeQuery(str);

        ObservableList<Flight> flightList = FXCollections.observableArrayList();
        while (rs.next()) {
            Flight flight = new Flight(
                    rs.getString("f_code"),
                    rs.getString("f_name"),
                    rs.getString("src"),
                    rs.getString("dst"),
                    rs.getString("capacity"),
                    rs.getString("class_code"),
                    rs.getString("class_name")
            );
            flightList.add(flight);
        }

        table.setItems(flightList);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static class Flight {
        private final StringProperty flightCode;
        private final StringProperty flightName;
        private final StringProperty source;
        private final StringProperty destination;
        private final StringProperty capacity;
        private final StringProperty classCode;
        private final StringProperty className;

        public Flight(String flightCode, String flightName, String source, String destination, String capacity, String classCode, String className) {
        this.flightCode = new SimpleStringProperty(flightCode);
        this.flightName = new SimpleStringProperty(flightName);
        this.source = new SimpleStringProperty(source);
        this.destination = new SimpleStringProperty(destination);
        this.capacity = new SimpleStringProperty(capacity);
        this.classCode = new SimpleStringProperty(classCode);
        this.className = new SimpleStringProperty(className);
    }

    public String getFlightCode() {
        return flightCode.get();
    }

    public StringProperty flightCodeProperty() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode.set(flightCode);
    }

    public String getFlightName() {
        return flightName.get();
    }

    public StringProperty flightNameProperty() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName.set(flightName);
    }

    public String getSource() {
        return source.get();
    }

    public StringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getDestination() {
        return destination.get();
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getCapacity() {
        return capacity.get();
    }

    public StringProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }

    public String getClassCode() {
        return classCode.get();
    }

    public StringProperty classCodeProperty() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode.set(classCode);
    }

    public String getClassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public void setClassName(String className) {
        this.className.set(className);
    }
}
}



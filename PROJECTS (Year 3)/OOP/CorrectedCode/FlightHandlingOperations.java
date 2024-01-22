package CorrectedCode;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class FlightHandlingOperations extends Application {
    private RouteList routeList = new RouteList();
    private FlightList flightList = new FlightList();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flight Handling Operations");

        Button addFlightButton = new Button("Add Flight");
        addFlightButton.setOnAction(e -> addFlight());

        TableView<Flight> flightTableView = createFlightTableView();

        Button displayAllFlightsButton = new Button("Display All Flights");
        displayAllFlightsButton.setOnAction(e -> displayAllFlights(flightTableView));

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(addFlightButton, displayAllFlightsButton, exitButton, flightTableView);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addFlight() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Flight");
        dialog.setHeaderText(null);

        Label typeLabel = new Label("Flight Type:");
        Label weightLabel = new Label("Flight Weight:");
        Label capacityLabel = new Label("Flight Capacity:");
        Label statusLabel = new Label("Flight Status:");
        Label fuelConsumptionLabel = new Label("Fuel Consumption:");
        Label reservationInfoLabel = new Label("Reservation Info:");
        Label ticketCostLabel = new Label("Ticket Cost:");

        TextField typeField = new TextField();
        TextField weightField = new TextField();
        TextField capacityField = new TextField();
        TextField statusField = new TextField();
        TextField fuelConsumptionField = new TextField();
        TextField reservationInfoField = new TextField();
        TextField ticketCostField = new TextField();

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        VBox content = new VBox(10, typeLabel, typeField, weightLabel, weightField, capacityLabel, capacityField,
                statusLabel, statusField, fuelConsumptionLabel, fuelConsumptionField, reservationInfoLabel,
                reservationInfoField, ticketCostLabel, ticketCostField);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                return typeField.getText() + " " + weightField.getText() + " " +
                        capacityField.getText() + " " + statusField.getText() + " " +
                        fuelConsumptionField.getText() + " " + reservationInfoField.getText() + " " +
                        ticketCostField.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            String[] inputArray = result.split(" ");
            String type = inputArray[0];
            double weight = Double.parseDouble(inputArray[1]);
            int capacity = Integer.parseInt(inputArray[2]);
            String status = inputArray[3];
            double fuelConsumption = Double.parseDouble(inputArray[4]);
            String reservationInfo = inputArray[5];
            Double ticketCost = Double.parseDouble(inputArray[6]);

            flightList.addFlight(routeList.getRoutes(), type, weight, capacity, status, fuelConsumption, reservationInfo, ticketCost);
            System.out.println("Flight added successfully!");
        });
    }

    private void displayAllFlights(TableView<Flight> tableView) {
        tableView.setItems(FXCollections.observableArrayList(flightList.getAllFlights()));
    }

    private TableView<Flight> createFlightTableView() {
        TableView<Flight> tableView = new TableView<>();

        TableColumn<Flight, String> typeColumn = new TableColumn<>("Flight Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("flightType"));

        TableColumn<Flight, Double> weightColumn = new TableColumn<>("Flight Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("flightWeight"));

        TableColumn<Flight, Integer> capacityColumn = new TableColumn<>("Flight Capacity");
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("flightCapacity"));

        TableColumn<Flight, String> statusColumn = new TableColumn<>("Flight Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));

        TableColumn<Flight, Double> fuelConsumptionColumn = new TableColumn<>("Fuel Consumption");
        fuelConsumptionColumn.setCellValueFactory(new PropertyValueFactory<>("flightFuelConsumption"));

        TableColumn<Flight, String> reservationInfoColumn = new TableColumn<>("Reservation Info");
        reservationInfoColumn.setCellValueFactory(new PropertyValueFactory<>("flightReservationInfo"));

        TableColumn<Flight, Double> ticketCostColumn = new TableColumn<>("Ticket Cost");
        ticketCostColumn.setCellValueFactory(new PropertyValueFactory<>("ticketCost"));

        tableView.getColumns().addAll(typeColumn, weightColumn, capacityColumn, statusColumn,
                fuelConsumptionColumn, reservationInfoColumn, ticketCostColumn);

        return tableView;
    }
}

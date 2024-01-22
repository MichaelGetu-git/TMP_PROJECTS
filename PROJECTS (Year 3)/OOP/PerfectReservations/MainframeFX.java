package PerfectReservations;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainframeFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AIRLINE RESERVATION MANAGEMENT SYSTEM");

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #f0f0f0;"); // Set background color

        root.getChildren().add(createMainContent());

        Scene scene = new Scene(root, 1400, 800);
       primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMainContent() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        
        Label welcomeLabel = new Label("Welcome To Airline, Your home in the sky");
        welcomeLabel.setStyle("-fx-text-fill: #3366cc; -fx-font-size: 20;");

        mainContent.getChildren().addAll(welcomeLabel, createMenu());
        
        return mainContent;
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();

        Menu airlineSystemMenu = new Menu("AIRLINE SYSTEM");
        airlineSystemMenu.setStyle("-fx-text-fill: #3366cc;");

        MenuItem flightDetailsItem = new MenuItem("FLIGHT_INFO");
        MenuItem reservationDetailsItem = new MenuItem("ADD_CUSTOMER_DETAILS");
        MenuItem passengerDetailsItem = new MenuItem("JOURNEY_DETAILS");
        MenuItem paymentDetailsItem = new MenuItem("PAYMENT_DETAILS");
        MenuItem cancellationItem = new MenuItem("CANCELLATION");

        airlineSystemMenu.getItems().addAll(
                flightDetailsItem,
                reservationDetailsItem,
                passengerDetailsItem,
                paymentDetailsItem,
                cancellationItem
        );

        Menu ticketMenu = new Menu("TICKET");
        ticketMenu.setStyle("-fx-text-fill: #cc0000;");

        Menu listMenu = new Menu("LIST");
        listMenu.setStyle("-fx-text-fill: #3366cc;");

        Menu miscMenu = new Menu("MISC");
        miscMenu.setStyle("-fx-text-fill: #cc0000;");

        menuBar.getMenus().addAll(airlineSystemMenu, ticketMenu, listMenu, miscMenu);

        flightDetailsItem.setOnAction(e -> new FlightInfoFX().start(new Stage()));
        reservationDetailsItem.setOnAction(e -> new Add_Customer().start(new Stage()));
        passengerDetailsItem.setOnAction(e -> new JourneyDetailsFX().start(new Stage()));
        paymentDetailsItem.setOnAction(e -> new PaymentDetailsFX().start(new Stage()));
        cancellationItem.setOnAction(e -> new CancelFX().start(new Stage()));

        return menuBar;
    }
}

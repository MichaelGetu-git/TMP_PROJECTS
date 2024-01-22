package PerfectReservations;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;

public class JourneyDetailsFX extends Application {

    private TableView<ObservableList<String>> table = new TableView<>();
    private ComboBox<String> comboBoxSource, comboBoxDestination;
    private Button showButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JOURNEY_DETAILS");

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20));

        HBox sourceDestinationBox = new HBox(10);
        sourceDestinationBox.getChildren().addAll(new Label("SOURCE"), createSourceComboBox(),
                new Label("DESTINATION"), createDestinationComboBox(), createShowButton());

        vBox.getChildren().addAll(new Label("JOURNEY DETAILS"), sourceDestinationBox, createTableView());

        primaryStage.setScene(new Scene(vBox, 860, 600));
        primaryStage.show();
    }

    private ComboBox<String> createSourceComboBox() {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Philippines", "China", "Russia", "United States", "Australia", "Saudi Arabia");
        comboBoxSource = new ComboBox<>(items);
        comboBoxSource.setValue("Philippines"); // Set default value
        return comboBoxSource;
    }

    private ComboBox<String> createDestinationComboBox() {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Philippines", "China", "Russia", "United States", "Australia", "Saudi Arabia");
        comboBoxDestination = new ComboBox<>(items);
        comboBoxDestination.setValue("Philippines"); // Set default value
        return comboBoxDestination;
    }

    private Button createShowButton() {
        showButton = new Button("SHOW");
        showButton.setOnAction(e -> handleShowButton());
        return showButton;
    }

    private void handleShowButton() {
        String src = comboBoxSource.getValue();
        String dst = comboBoxDestination.getValue();

        try {
            conn c = new conn();
                    
                    String str = "select pnr_no,ticket_id,f_code,jny_date,jny_time,src,dst from reservation where src = '"+src+"' and dst = '"+dst+"'";
                    ResultSet rs=c.s.executeQuery(str);
                   
            if (rs.next()) {
                populateTableView(rs);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No Flights between Source and Destination");
                alert.showAndWait();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void populateTableView(ResultSet rs) {
        table.getItems().clear();
        table.getColumns().clear();

        try {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> col = new TableColumn<>(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(param -> {
                    SimpleStringProperty property = new SimpleStringProperty(param.getValue().get(j));
                    return property;
                });
                table.getColumns().add(col);
            }

            do {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                table.getItems().add(row);
            } while (rs.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private TableView<ObservableList<String>> createTableView() {
        table.setMinHeight(200);
        return table;
    }
}

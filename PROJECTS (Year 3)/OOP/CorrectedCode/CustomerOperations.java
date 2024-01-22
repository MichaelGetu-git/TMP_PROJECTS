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

public class CustomerOperations extends Application {
    private CustomerList customerList = new CustomerList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Operations");

          TableView<Customer> tableView = new TableView<>();
    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<Customer, String> userIDColumn = new TableColumn<>("User ID");
    userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

    TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

    TableColumn<Customer, String> passwordColumn = new TableColumn<>("Password");
    passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

    TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone Number");
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

    TableColumn<Customer, Integer> ageColumn = new TableColumn<>("Age");
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

    tableView.getColumns().addAll(nameColumn, userIDColumn, emailColumn, passwordColumn, phoneColumn, ageColumn);
    
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(10, 10, 10, 10));

    Button addButton = new Button("Add Customer");
    addButton.setOnAction(e -> addCustomer());

    Button searchButton = new Button("Search Customer");
    searchButton.setOnAction(e -> searchCustomer());

    Button displayButton = new Button("Display All Customers");
    displayButton.setOnAction(e -> displayAllCustomers(tableView, layout)); // Pass the layout as well

    Button exitButton = new Button("Exit");
    exitButton.setOnAction(e -> {
        System.out.println("Exiting Customer Operations.");
        primaryStage.close();
    });

    layout.getChildren().addAll(addButton, searchButton, displayButton, tableView, exitButton);

    Scene scene = new Scene(layout, 600, 400); // Increase the size to ensure visibility
    primaryStage.setScene(scene);
    primaryStage.show();
}

    private void addCustomer() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Customer");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter customer name:");
        String name = dialog.showAndWait().orElse("");

        dialog.setContentText("Enter customer userID:");
        String userID = dialog.showAndWait().orElse("");

        dialog.setContentText("Enter customer email:");
        String email = dialog.showAndWait().orElse("");

        dialog.setContentText("Enter customer password:");
        String password = dialog.showAndWait().orElse("");

        dialog.setContentText("Enter customer phone number:");
        String phone = dialog.showAndWait().orElse("");

        dialog.setContentText("Enter customer age:");
        int age = Integer.parseInt(dialog.showAndWait().orElse("0"));

        Customer newCustomer = new Customer(userID, name, email, password, phone, age);
        customerList.addCustomer(newCustomer);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Customer added successfully!");
        alert.showAndWait();
    }

    private void searchCustomer() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Customer");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter search property (name, userID, email, phone):");
        String property = dialog.showAndWait().orElse("");

        dialog.setContentText("Enter search input:");
        String input = dialog.showAndWait().orElse("");

        Customer foundCustomer = customerList.searchCustomer(property, input);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search Result");
        alert.setHeaderText(null);

        if (foundCustomer != null) {
            alert.setContentText("Customer found:\n" + foundCustomer);
        } else {
            alert.setContentText("No customer found with the specified criteria.");
        }

        alert.showAndWait();
    }
 private void displayAllCustomers(TableView<Customer> tableView, VBox layout) {
    System.out.println("Displaying all customers...");

    ObservableList<Customer> customers = FXCollections.observableArrayList(customerList.getAllCustomers());
    tableView.setItems(customers);

    System.out.println("Displayed customers: " + customers.size());

    // Ensure layout is refreshed
    layout.requestLayout();
    layout.requestFocus();
}
}

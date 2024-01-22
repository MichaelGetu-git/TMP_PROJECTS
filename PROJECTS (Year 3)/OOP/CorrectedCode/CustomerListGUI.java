package CorrectedCode;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CustomerListGUI extends Application {

    private CustomerList customerList = new CustomerList();
    private TableView<Customer> customerTable = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Management System");

        // Create tabs
        TabPane tabPane = new TabPane();
        Tab viewTab = createViewTab();
        Tab addTab = createAddTab();
        Tab searchTab = createSearchTab();
        Tab deleteTab = createDeleteTab();

        tabPane.getTabs().addAll(viewTab, addTab, searchTab, deleteTab);

        // Create scene
        Scene scene = new Scene(tabPane, 600, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private Tab createViewTab() {
        Tab tab = new Tab("View Customers");
        tab.setClosable(false);

        // Create table columns
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> userIDColumn = new TableColumn<>("User ID");
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Customer, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        // Add columns to table
        customerTable.getColumns().addAll(nameColumn, userIDColumn, emailColumn, phoneColumn, ageColumn);

        // Load data into the table
        customerTable.setItems(CustomerList.getAllCustomers());

        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(customerTable);

        tab.setContent(layout);

        return tab;
    }

    private Tab createAddTab() {
        Tab tab = new Tab("Add Customer");
        tab.setClosable(false);

        // Create form elements
        TextField nameField = new TextField();
        TextField userIDField = new TextField();
        TextField emailField = new TextField();
        TextField phoneField = new TextField();
        TextField ageField = new TextField();
        Button addButton = new Button("Add Customer");

        // Add button click event
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String userID = userIDField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            int age = Integer.parseInt(ageField.getText());

            Customer newCustomer = new Customer(userID, name, email, "", phone, age);
            customerList.addCustomer(newCustomer);

            // Update table
            customerTable.setItems(CustomerList.getAllCustomers());

            // Clear form fields
            nameField.clear();
            userIDField.clear();
            emailField.clear();
            phoneField.clear();
            ageField.clear();
        });

        // Create layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(10);

        layout.addRow(0, new Label("Name:"), nameField);
        layout.addRow(1, new Label("User ID:"), userIDField);
        layout.addRow(2, new Label("Email:"), emailField);
        layout.addRow(3, new Label("Phone:"), phoneField);
        layout.addRow(4, new Label("Age:"), ageField);
        layout.addRow(5, addButton);

        tab.setContent(layout);

        return tab;
    }

    private Tab createSearchTab() {
        Tab tab = new Tab("Search Customer");
        tab.setClosable(false);

        // Create form elements
        TextField searchInputField = new TextField();
        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        // Add button click event
        searchButton.setOnAction(e -> {
            String property = "userID"; // Default search property (you can customize this)
            String input = searchInputField.getText();

            Customer foundCustomer = customerList.searchCustomer(property, input);

            if (foundCustomer != null) {
                resultArea.setText("Customer Found:\n" + foundCustomer.toString());
            } else {
                resultArea.setText("Customer not found.");
            }
        });

        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(new Label("Enter search input:"), searchInputField, searchButton, resultArea);

        tab.setContent(layout);

        return tab;
    }

    private Tab createDeleteTab() {
        Tab tab = new Tab("Delete Customer");
        tab.setClosable(false);

        // Create form elements
        TextField deleteInputField = new TextField();
        Button deleteButton = new Button("Delete");
        TextArea deleteResultArea = new TextArea();
        deleteResultArea.setEditable(false);

        // Add button click event
        deleteButton.setOnAction(e -> {
            String property = "userID"; // Default delete property (you can customize this)
            String input = deleteInputField.getText();

            Customer deletedCustomer = customerList.searchCustomer(property, input);

            if (deletedCustomer != null) {
                customerList.getAllCustomers().remove(deletedCustomer);
                deleteResultArea.setText("Customer Deleted:\n" + deletedCustomer.toString());
            } else {
                deleteResultArea.setText("Customer not found.");
            }
        });

        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(new Label("Enter delete input:"), deleteInputField, deleteButton, deleteResultArea);

        tab.setContent(layout);

        return tab;
    }
}

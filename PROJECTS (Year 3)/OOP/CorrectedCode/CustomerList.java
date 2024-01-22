/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CorrectedCode;

/**
 *
 * @author HP 15
 */

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerList {
    private static List<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static Customer searchCustomer(String property, String input) {
        for (Customer customer : customers) {
            switch (property) {
                case "name":
                    if (customer.getName().equalsIgnoreCase(input)) {
                        return customer;
                    }
                    break;
                case "userID":
                    if (customer.getUserID().equals(input)) {
                        return customer;
                    }
                    break;
                case "email":
                    if (customer.getEmail().equalsIgnoreCase(input)) {
                        return customer;
                    }
                    break;
                case "phone":
                    if (customer.getPhone().equals(input)) {
                        return customer;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid search property: " + property);
            }
        }

        return null; // No match found
    }
    
      public static ObservableList<Customer> getAllCustomers() {
        return FXCollections.observableArrayList(customers);
    }


    public static void displayCustomers() {
        System.out.println("All Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

   
}

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.sql.*;

public class PaymentDetailsFX extends Application {

    private TextField textField;
    private TableView<PaymentRecord> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initialize(primaryStage);
    }

    private void initialize(Stage primaryStage) {
        primaryStage.setTitle("PAYMENT_DETAILS");

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER_LEFT);

        Label sectorLabel = new Label("PAYMENT DETAILS");
        sectorLabel.setStyle("-fx-text-fill: blue; -fx-font-size: 18;");

        Label pnrLabel = new Label("PNR NO:");
        textField = new TextField();
        Button showButton = new Button("SHOW");
        showButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

        inputBox.getChildren().addAll(pnrLabel, textField, showButton);

        table = new TableView<>();
        TableColumn<PaymentRecord, String> pnrColumn = new TableColumn<>("PNR_NO");
        TableColumn<PaymentRecord, String> paidAmountColumn = new TableColumn<>("PAID_AMOUNT");
        TableColumn<PaymentRecord, String> payDateColumn = new TableColumn<>("PAY_DATE");
        TableColumn<PaymentRecord, String> chequeNoColumn = new TableColumn<>("CHEQUE_NO");
        TableColumn<PaymentRecord, String> cardNoColumn = new TableColumn<>("CARD_NO");
        TableColumn<PaymentRecord, String> phoneNoColumn = new TableColumn<>("PHONE_NO");

        pnrColumn.setCellValueFactory(new PropertyValueFactory<>("pnrNo"));
        paidAmountColumn.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        payDateColumn.setCellValueFactory(new PropertyValueFactory<>("payDate"));
        chequeNoColumn.setCellValueFactory(new PropertyValueFactory<>("chequeNo"));
        cardNoColumn.setCellValueFactory(new PropertyValueFactory<>("cardNo"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        table.getColumns().addAll(pnrColumn, paidAmountColumn, payDateColumn, chequeNoColumn, cardNoColumn, phoneNoColumn);

        mainLayout.getChildren().addAll(sectorLabel, inputBox, table);

        showButton.setOnAction(e -> fetchDataFromDatabase());

        Scene scene = new Scene(mainLayout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
private void fetchDataFromDatabase() {
    try {
        String code = textField.getText();  
        conn c = new conn();
        String str = "select pnr_no, paid_amt, pay_date, cheque_no, card_no, ph_no from payment where pnr_no = '"+code+"'";
        ResultSet rs = c.s.executeQuery(str);
                  
        ObservableList<PaymentRecord> data = FXCollections.observableArrayList();

        while (rs.next()) {
            PaymentRecord record = new PaymentRecord(
                    rs.getString("pnr_no"),
                    rs.getString("paid_amt"),
                    rs.getString("pay_date"),
                    rs.getString("cheque_no"),
                    rs.getString("card_no"),
                    rs.getString("ph_no")
            );
            data.add(record);
        }

        table.setItems(data);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static class PaymentRecord {
        private final String pnrNo;
        private final String paidAmount;
        private final String payDate;
        private final String chequeNo;
        private final String cardNo;
        private final String phoneNo;

        public PaymentRecord(String pnrNo, String paidAmount, String payDate, String chequeNo, String cardNo, String phoneNo) {
            this.pnrNo = pnrNo;
            this.paidAmount = paidAmount;
            this.payDate = payDate;
            this.chequeNo = chequeNo;
            this.cardNo = cardNo;
            this.phoneNo = phoneNo;
        }

        public String getPnrNo() {
            return pnrNo;
        }

        public String getPaidAmount() {
            return paidAmount;
        }

        public String getPayDate() {
            return payDate;
        }

        public String getChequeNo() {
            return chequeNo;
        }

        public String getCardNo() {
            return cardNo;
        }

        public String getPhoneNo() {
            return phoneNo;
        }
    }
}

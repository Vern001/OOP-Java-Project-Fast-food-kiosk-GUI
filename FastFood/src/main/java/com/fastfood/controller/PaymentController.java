package com.fastfood.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class PaymentController {

    @FXML
    private Label totalAmountLabel;

    private double finalAmount;

    public void setOrderTotal(double total) {
        this.finalAmount = total;
        if (totalAmountLabel != null) {
            totalAmountLabel.setText(String.format("Amount Due: $%.2f", total));
        }
    }

    @FXML
    private void handleCardPayment(ActionEvent event) throws IOException {
        processPayment(event, "Credit/Debit Card");
    }

    @FXML
    private void handleCashPayment(ActionEvent event) throws IOException {
        processPayment(event, "Cash at Counter");
    }

    @FXML
    private void handleMobilePayment(ActionEvent event) throws IOException {
        processPayment(event, "Mobile Wallet");
    }

    private void processPayment(ActionEvent event, String method) throws IOException {
        navigateToReceipt(event);
    }

    @FXML
    private void handleBackToCart(ActionEvent event) throws IOException {
        Parent cartRoot = FXMLLoader.load(getClass().getResource("/com/fastfood/view/cartview.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(cartRoot, 800, 600));
    }

    private void navigateToReceipt(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fastfood/view/receiptview.fxml"));
        Parent receiptRoot = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(receiptRoot, 800, 600));
    }
}
package com.fastfood.controller;
import com.fastfood.service.OrderService;
import com.fastfood.model.CartItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class CartController {

    @FXML
    private VBox cartItemsContainer;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Button placeOrderButton;

    @FXML
    public void initialize() {
        populateCart();
        refreshTotals();
    }

    private void populateCart() {
        cartItemsContainer.getChildren().clear();

        for (CartItem item : OrderService.getInstance().getCartItems()) {
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(15, 25, 15, 25));
            row.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #E0E0E0; -fx-border-width: 0 0 1 0;");

            Label details = new Label(item.getMenuItem().getName());
            details.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

            //buttons to add and minus
            HBox quantityControl = new HBox(10);
            quantityControl.setAlignment(Pos.CENTER);
            quantityControl.setPadding(new Insets(0, 20, 0, 20));

            Button minus = new Button("-");
            Label Qlabel = new Label(String.valueOf(item.getQuantity()));
            Qlabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            Button plus = new Button("+");

            String btnStyle = "-fx-background-color: #F5F5F5; -fx-text-fill: #333333; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 5; -fx-min-width: 30px; -fx-cursor: hand;";
            minus.setStyle(btnStyle);
            plus.setStyle(btnStyle);

            minus.setOnAction(actionEvent -> {
                if (item.getQuantity() > 1) {
                    item.decrementQuantity();
                } else {
                    OrderService.getInstance().removeItem(item.getMenuItem());
                }
                populateCart();
                refreshTotals();
            });
            plus.setOnAction(actionEvent -> {
                item.incrementQuantity();
                populateCart();
                refreshTotals();
            });
            //end of buttons
            quantityControl.getChildren().addAll(minus, Qlabel, plus);

            Label price = new Label(String.format("$%.2f", item.getTotalPrice()));
            price.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #126aa6; -fx-padding: 0 0 0 20;");

            javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
            javafx.scene.layout.HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

            Button delete = new Button("X");

            delete.setOnAction(e -> {
                OrderService.getInstance().removeItem(item.getMenuItem());
                populateCart();
                refreshTotals();
            });
            delete.setStyle(
                    "-fx-background-color: #FFEBEE; " +
                            "-fx-text-fill: #D32F2F; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 16px; " +
                            "-fx-background-radius: 8; " +
                            "-fx-min-width: 40px; " +
                            "-fx-min-height: 40px; " +
                            "-fx-cursor: hand;"
            );

            row.getChildren().addAll(details, quantityControl, price, spacer, delete);
            cartItemsContainer.getChildren().add(row);
        }
    }

    private void refreshTotals() {
        var cartItems = OrderService.getInstance().getCartItems();
        boolean isCartEmpty = cartItems.isEmpty();
        if (placeOrderButton != null) {
            placeOrderButton.setDisable(isCartEmpty);
        }
        double subtotal = OrderService.getInstance().calculateSubtotal();
        double tax = subtotal * 0.08;
        double grandTotal = subtotal + tax;

        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        taxLabel.setText(String.format("Tax (8%%): $%.2f", tax));
        totalLabel.setText(String.format("Grand Total: $%.2f", grandTotal));
    }

    @FXML
    private void handleBackToMenu(ActionEvent event) throws IOException {
        Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/fastfood/view/orderview.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menuRoot, 800, 600));
    }

    @FXML
    private void handlePlaceOrder(ActionEvent event) throws IOException {
        if (OrderService.getInstance().getCartItems().isEmpty()) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fastfood/view/paymentview.fxml"));
        Parent paymentRoot = loader.load();
        PaymentController paymentController = loader.getController();
        double subtotal = OrderService.getInstance().calculateSubtotal();
        double tax = subtotal * 0.08;
        double grandTotalWithTax = subtotal + tax;
        paymentController.setOrderTotal(grandTotalWithTax);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(paymentRoot, 800, 600));
    }
}
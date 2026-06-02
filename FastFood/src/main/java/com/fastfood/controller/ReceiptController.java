package com.fastfood.controller;

import com.fastfood.service.OrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class ReceiptController {

    @FXML
    private Label orderNumberLabel;

    @FXML
    public void initialize() {
        Random random=new Random();
        int orderNum = 100+random.nextInt(900);
        orderNumberLabel.setText("#"+orderNum);

        OrderService.getInstance().getCartItems().clear();
    }

    @FXML
    private void handleRestart(ActionEvent event) throws IOException {
        Parent mainRoot=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/fastfood/view/mainview.fxml")));
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(mainRoot, 800, 600));
    }
}
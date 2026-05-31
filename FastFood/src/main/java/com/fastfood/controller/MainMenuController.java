package com.fastfood.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {
    @FXML
    private void handleStartOrder(ActionEvent event) {
        try {
            Parent orderViewRoot=FXMLLoader.load(getClass().getResource("/com/fastfood/view/orderview.fxml"));
            Stage currentStage=(Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(orderViewRoot, 800, 600));
            currentStage.show();
        } catch (IOException e) {
            System.out.println("Error: Could not load orderview.fxml.");
            e.printStackTrace();
        }
    }
}
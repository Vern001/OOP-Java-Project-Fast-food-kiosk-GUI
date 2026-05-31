package com.fastfood;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/fastfood/view/mainview.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root, 800, 600);
            primaryStage.setTitle("Fast Food Ordering System");
            Image appIcon = new Image(getClass().getResourceAsStream("/com/fastfood/images/logo.png"));
            primaryStage.getIcons().add(appIcon);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("main view fxml file failed to load");

        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
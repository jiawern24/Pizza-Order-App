package com.rupizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main class for RU Pizzeria
 *
 * @author Jia Wern Chong
 */
public class PizzeriaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaApplication.class.getResource("MainMenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 500);
        stage.setTitle("RU Pizza");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
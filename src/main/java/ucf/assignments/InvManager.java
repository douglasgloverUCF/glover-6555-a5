/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Douglas Glover
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InvManager extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainWindow.fxml")));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Inventory Manager");
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
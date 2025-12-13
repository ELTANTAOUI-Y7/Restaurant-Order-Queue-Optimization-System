package com.restaurant;

import com.restaurant.ui.RestaurantController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for Restaurant Order & Queue Optimization System
 * 
 * This is the entry point of the JavaFX application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/restaurant.fxml"));
        Parent root = loader.load();
        
        // Get the controller and initialize it
        RestaurantController controller = loader.getController();
        controller.initialize();
        
        // Set up the stage
        primaryStage.setTitle("Restaurant Order & Queue Optimization System");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    /**
     * Main method
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}


package com.dedale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dedale.business.RootLayout;
import com.dedale.frmwrk.view.controller.ControllerManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Lancement de l'application.
 */
public class RpgManagerBootstrap extends Application {
    
    private static final Logger LOGGER = LogManager.getLogger(RpgManagerBootstrap.class);
    
    private ControllerManager controllerManager;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            controllerManager = new ControllerManager();
            controllerManager.registerControllers();
            
            // Load root layout from fxml file.
            RootLayout rootController = controllerManager.getRootController();
            rootController.setPrimaryStage(primaryStage);
            BorderPane rootLayout = rootController.getLayout();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("RPG Manager");
            primaryStage.show();
        } catch (Exception e) {
            LOGGER.error("Erreur applicative grave.", e);
            throw e;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

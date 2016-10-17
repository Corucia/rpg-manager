package com.dedale.business;

import com.dedale.business.ability.ihm.view.AbilityTemplateListView;
import com.dedale.frmwrk.view.controller.AbstractController;
import com.dedale.frmwrk.view.controller.ControllerException;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Controller racine de l'application.
 */
public class RootLayout extends AbstractController<BorderPane> {
    
    private Stage primaryStage;
    
    @Override
    protected void postInitialize() throws ControllerException {
        AbilityTemplateListView techNodeTemplateListView = getControllerManager().getController(AbilityTemplateListView.class);
        layout.setCenter(techNodeTemplateListView.getLayout());
    }
    
    public Window getStage() {
        return primaryStage;
    }
    
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
}

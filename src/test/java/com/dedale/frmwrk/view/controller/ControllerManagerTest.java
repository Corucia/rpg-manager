package com.dedale.frmwrk.view.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.layout.Pane;

public class ControllerManagerTest {

    private ControllerManager controllerManager;

    @Before
    public void beforeName() throws Exception {
        controllerManager = new ControllerManager();
    }
    
    @Test
    public void loadController_should_instanciateController() throws Exception {
        // Arrange
        
        // Act
        MyTestController loadController = controllerManager.loadController(MyTestController.class);
        
        // Assert
        assertThat(loadController).isNotNull();
    }

    // Utilitaires
    
    private static final class MyTestController extends AbstractController<Pane>{
    }
    
}

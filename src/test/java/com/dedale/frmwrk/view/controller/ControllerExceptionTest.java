package com.dedale.frmwrk.view.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ControllerExceptionTest {
    
    @Test
    public void ControllerException_should_support_message_without_parameters() throws Exception {
        // Arrange
        String messageWithoutParameters = "A message";
        
        // Act
        ControllerException controllerException = new ControllerException(messageWithoutParameters);
        
        // Assert
        assertThat(controllerException).isNotNull();
    }
    
    @Test
    public void ControllerException_should_contain_initial_message_when_it_does_not_contains_parameters() throws Exception {
        // Arrange
        String messageWithoutParameters = "A message";
        
        // Act
        ControllerException controllerException = new ControllerException(messageWithoutParameters);
        
        // Assert
        assertThat(controllerException.getMessage()).isEqualTo(messageWithoutParameters);
    }
    
    @Test
    public void ControllerException_should_contain_message_with_replaced_parameter_when_one_parameter_defined() throws Exception {
        // Arrange
        String messageWithoutParameters = "A message with {0} parameter";
        
        // Act
        ControllerException controllerException = new ControllerException(messageWithoutParameters, "ONE");
        
        // Assert
        assertThat(controllerException.getMessage()).isEqualTo("A message with ONE parameter");
    }
    
    @Test
    public void ControllerException_should_contain_message_multiple_replacements_when_one_parameter_defined() throws Exception {
        // Arrange
        String messageWithoutParameters = "A message with {0} parameter. Only {0} parameter given.";
        
        // Act
        ControllerException controllerException = new ControllerException(messageWithoutParameters, "ONE");
        
        // Assert
        assertThat(controllerException.getMessage()).isEqualTo("A message with ONE parameter. Only ONE parameter given.");
    }
    
    @Test
    public void ControllerException_should_contain_message_with_correct_replacement_when_three_parameters_defined() throws Exception {
        // Arrange
        String messageWithoutParameters = "A message with {2} parameters. First is {0}, second is {1}, third is {2}.";
        
        // Act
        ControllerException controllerException = new ControllerException(messageWithoutParameters, "ONE", "TWO", "THREE");
        
        // Assert
        assertThat(controllerException.getMessage()).isEqualTo("A message with THREE parameters. First is ONE, second is TWO, third is THREE.");
    }
    
}

package com.dedale.business.ability.ihm.view;

import java.util.function.Supplier;

import com.dedale.business.ability.model.AbilityTemplate;
import com.dedale.frmwrk.view.controller.AbstractController;
import com.dedale.frmwrk.view.controller.ControllerException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Formulaire de mise à jour d'un élément d'arbre technologique
 * @back : {@link AbilityTemplateListView}
 */
public class AbilityTemplateCreateForm extends AbstractController<AnchorPane> {
    
    @FXML
    private TextField nameLabel;
    @FXML
    private TextArea conditionsLabel;
    @FXML
    private TextArea descriptionLabel;
    
    private DialogAction validateAction;
    private DialogAction cancelAction;
    private Stage dialogStage;
    
    @FXML
    private void validate() throws ControllerException {
        validateAction.execute();
    }
    
    @FXML
    private void cancel() throws ControllerException {
        cancelAction.execute();
    }
    
    private void showTechNodeTemplateDetails(AbilityTemplate abilityTemplate) {
        if (abilityTemplate != null) {
            nameLabel.setText(abilityTemplate.getName());
            conditionsLabel.setText(abilityTemplate.getConditions());
            descriptionLabel.setText(abilityTemplate.getDescription());
        } else {
            nameLabel.setText("");
            conditionsLabel.setText("");
            descriptionLabel.setText("");
        }
    }
    
    public void initDialog(Supplier<AbilityTemplate> updateItemProvider, DialogAction viewCallback) throws ControllerException {
        // Create the dialog Stage.
        if (dialogStage == null) {
            
            dialogStage = new Stage();
            dialogStage.setTitle("Dialog -> Edit Tech");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getControllerManager().getRootController().getStage());
            Scene scene = new Scene(layout);
            dialogStage.setScene(scene);
        }
        
        AbilityTemplate abilityTemplate = updateItemProvider.get();
        showTechNodeTemplateDetails(abilityTemplate);
        validateAction = () -> {
            updateAbilityTemplate(abilityTemplate);
            viewCallback.execute();
            dialogStage.close();
        };
        cancelAction = () -> {
            dialogStage.close();
        };
        dialogStage.showAndWait();
    }
    
    @FunctionalInterface
    public static interface DialogAction {
        void execute() throws ControllerException;
    }
    
    private void updateAbilityTemplate(AbilityTemplate abilityTemplate) {
        abilityTemplate.setName(nameLabel.getText());
        abilityTemplate.setConditions(conditionsLabel.getText());
        abilityTemplate.setDescription(descriptionLabel.getText());
    }
    
}

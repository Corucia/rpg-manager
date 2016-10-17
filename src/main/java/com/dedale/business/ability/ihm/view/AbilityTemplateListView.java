package com.dedale.business.ability.ihm.view;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dedale.RpgManagerBootstrap;
import com.dedale.business.ability.model.AbilityTemplate;
import com.dedale.frmwrk.model.dao.XMLMapper;
import com.dedale.frmwrk.view.controller.AbstractController;
import com.dedale.frmwrk.view.controller.ControllerException;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Liste des éléments d'un arbre technologique.
 */
public class AbilityTemplateListView extends AbstractController<AnchorPane> {
    
    private static final Logger LOGGER = LogManager.getLogger(AbilityTemplateListView.class);
    
    private static final String DATA_FILE_NAME = "data/abilityTemplateList.xml";
    
    @FXML
    private TableView<AbilityTemplate> abilityTemplateList;
    @FXML
    private TableColumn<AbilityTemplate, String> nameColumn;
    
    @FXML
    private Label nameLabel;
    @FXML
    private Label conditionsLabel;
    @FXML
    private Label descriptionLabel;
    
    private XMLMapper xmlMapper = new XMLMapper();
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit details for the selected person.
     * 
     * @throws ControllerException
     */
    @FXML
    private void handleUpdate() throws ControllerException {
        AbilityTemplateUpdateForm updateForm = getControllerManager().getController(AbilityTemplateUpdateForm.class);
        updateForm.initDialog(() -> abilityTemplateList.getSelectionModel().getSelectedItem(), () -> {
            saveAbilityTemplateList();
            refresh();
        });
    }
    
    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @Override
    protected void postInitialize() throws ControllerException {
        loadAbilityTemplateList();
        nameColumn.setCellValueFactory(cellData -> toViewProperty(cellData.getValue().getName()));
        
        abilityTemplateList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTechNodeTemplateDetails(newValue));
    }
    
    private void loadAbilityTemplateList() throws ControllerException {
        try {
            AbilityTemplateListWrapper imported = getMapper().importFrom(AbilityTemplateListWrapper.class, getResourceFile());
            
            Collection<AbilityTemplate> abilityTemplates = imported.getAbilityTemplateList();
            ObservableList<AbilityTemplate> observableAbilityTemplateList = FXCollections.observableArrayList(abilityTemplates);
            this.abilityTemplateList.setItems(observableAbilityTemplateList);
        } catch (JAXBException | IOException e) {
            LOGGER.error("Impossible de charger la liste des capacités.", e);
            throw new ControllerException("Impossible de cherger la liste des capacités.", e);
        }
    }
    
    private void saveAbilityTemplateList() throws ControllerException {
        try {
            AbilityTemplateListWrapper wrapper = new AbilityTemplateListWrapper(abilityTemplateList.getItems());
            getMapper().exportInto(wrapper, getResourceFile());
        } catch (JAXBException | IOException e) {
            LOGGER.error("Impossible d'enregistrer la liste des capacités.", e);
            throw new ControllerException("Impossible de sauvegarder la liste des capacités.", e);
        }
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
    
    private Property<String> toViewProperty(String value) {
        return new SimpleStringProperty(value);
    }
    
    private void refresh() {
        showTechNodeTemplateDetails(abilityTemplateList.getSelectionModel().getSelectedItem());
    }
    
    private File getResourceFile() throws IOException {
        File file = new File(DATA_FILE_NAME);
        if (!file.exists()) {
            LOGGER.warn("Fichier à créer : " + file + ", path:" + file.getAbsolutePath());
            file.createNewFile();
        }
        return file;
    }
    
    private XMLMapper getMapper() {
        return xmlMapper;
    }
    
}

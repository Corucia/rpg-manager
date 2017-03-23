package com.dedale.business.ability.ihm.view;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dedale.business.ability.model.AbilityTemplate;
import com.dedale.business.ability.model.AbilityTemplateViewBean;
import com.dedale.frmwrk.model.dao.XMLMapper;
import com.dedale.frmwrk.view.controller.AbstractController;
import com.dedale.frmwrk.view.controller.ControllerException;

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
    private TableView<AbilityTemplateViewBean> abilityTemplateList;
    @FXML
    private TableColumn<AbilityTemplateViewBean, String> nameColumn;
    
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
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().name());
        
        abilityTemplateList
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showAbilityTemplate(newValue));
    }
    
    private void loadAbilityTemplateList() throws ControllerException {
        try {
            AbilityTemplateListWrapper imported = getMapper().importFrom(AbilityTemplateListWrapper.class, getResourceFile());
            Collection<AbilityTemplateViewBean> abilityTemplates = imported
                    .getAbilityTemplateList()
                    .stream()
                    .map(AbilityTemplateViewBean::new)
                    .collect(Collectors.toList());
            
            ObservableList<AbilityTemplateViewBean> observableAbilityTemplateList = FXCollections.observableArrayList(abilityTemplates);
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
    
    private void showAbilityTemplate(AbilityTemplate abilityTemplate) {
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
    
    private void refresh() {
        //        showAbilityTemplate(abilityTemplateList.getSelectionModel().getSelectedItem());
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

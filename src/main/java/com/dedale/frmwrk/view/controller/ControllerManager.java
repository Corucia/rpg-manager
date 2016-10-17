package com.dedale.frmwrk.view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.dedale.business.RootLayout;
import com.dedale.business.ability.ihm.view.AbilityTemplateListView;
import com.dedale.business.ability.ihm.view.AbilityTemplateUpdateForm;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Objet permettant d'orchercher les controllers dans l'application.
 */
public class ControllerManager {
    
    private static final String FXML_SUFFIX = ".fxml";
    
    private static final Class<RootLayout> ROOT_CONTROLLER = RootLayout.class;
    
    private Map<Class<? extends AbstractController>, AbstractController> controllers = new HashMap<>();
    
    /**
     * Enregistre l'ensemble des controllers de l'application.
     */
    public void registerControllers() throws ControllerException {
        Collection<Class<? extends AbstractController>> controllerClassList = getControllerClassList();
        for (Class<? extends AbstractController> controllerClass : controllerClassList) {
            registerController(controllerClass);
        }
        for (Class<? extends AbstractController> controllerClass : controllerClassList) {
            initializeController(controllerClass);
        }
    }
    
    /**
     * Renvoie un controller enregistré dans le manager, notrmalement chargé au démarrage de l'application.
     */
    public <P extends Pane, C extends AbstractController<P>> C getController(Class<? extends C> controllerClass)
            throws ControllerException {
        C abstractController = (C) controllers.get(controllerClass);
        if (abstractController != null) {
            return abstractController;
        }
        throw new ControllerException("Impossible de récupérer le controller {0}. Il n\'a pas été chargé par l\'application.",
                controllerClass.getSimpleName());
    }
    
    /**
     * Revnoie le controller racien de l'application.
     */
    public final RootLayout getRootController() throws ControllerException {
        return getController(ROOT_CONTROLLER);
    }
    
    /**
     * Enregistrement à l'arrache des classes de controller disponinl.
     */
    private Collection<Class<? extends AbstractController>> getControllerClassList() {
        Collection<Class<? extends AbstractController>> controllerClassList = new ArrayList<>();
        controllerClassList.add(ROOT_CONTROLLER);
        controllerClassList.add(AbilityTemplateListView.class);
        controllerClassList.add(AbilityTemplateUpdateForm.class);
        return controllerClassList;
    }
    
    // Logique interne
    
    private <P extends Pane, C extends AbstractController<P>> void registerController(Class<? extends C> controllerClass) throws ControllerException {
        C controller = loadController(controllerClass);
        controllers.put(controllerClass, controller);
    }
    
    /**
     * Effecture le chargement du controller décrit par la classe donnée.
     * <p>
     * A terme, il faudra faire de l'injection, au lieu d'appeler la méthode <code>initialize</code>.
     */
    <P extends Pane, C extends AbstractController<P>> C loadController(Class<? extends AbstractController<P>> controllerClass) throws ControllerException {
        try {
            FXMLLoader loader = createControllerLoader(controllerClass);
            
            P layout = loader.load();
            C controller = loader.getController();
            
            controller.initialize(this, layout);
            return controller;
        } catch (Exception e) {
            throw new ControllerException("Impossible de charger le controller " + controllerClass.getSimpleName(), e);
        }
    }
    
    private <P extends Pane, C extends AbstractController<P>> void initializeController(Class<? extends C> controllerClass)
            throws ControllerException {
        C controller = getController(controllerClass);
        controller.postInitialize();
    }
    
    /**
     * Créer l'objet permettant de chercher le controller de classe donnée.
     */
    private <P extends Pane, C extends AbstractController<P>> FXMLLoader createControllerLoader(Class<? extends C> controllerClass) {
        FXMLLoader loader = new FXMLLoader();
        String controllerTemplate = controllerClass.getSimpleName() + FXML_SUFFIX;
        URL resource = controllerClass.getResource(controllerTemplate);
        loader.setLocation(resource);
        return loader;
    }
    
}

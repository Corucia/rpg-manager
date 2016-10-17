package com.dedale.frmwrk.view.controller;

import javafx.scene.layout.Pane;

/**
 * Représsente le socle technique des controllers de l'application.
 * 
 * @param <L> Le type de layout renvoyé par le controller
 */
public abstract class AbstractController<L extends Pane> {
    
    protected L layout;
    private ControllerManager controllerManager;
    
    void initialize(ControllerManager controllerManager, L layout) throws ControllerException {
        this.controllerManager = controllerManager;
        this.layout = layout;
    }

    /** 
     * Renvoie le layout affiché par ce controller.
     */
    public final L getLayout() {
        return layout;
    }

    /** 
     * Renvoie l'objet responsable de la gestion de tous les controllers de l'application.
     */
    protected final ControllerManager getControllerManager() {
        return controllerManager;
    }
    
    /**
     * Point d'extension un peu cavalier pour compléter l'initialization. 
     * @throws ControllerException 
     */
    protected void postInitialize() throws ControllerException {
    }
    
}

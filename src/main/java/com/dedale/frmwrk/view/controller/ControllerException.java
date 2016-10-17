package com.dedale.frmwrk.view.controller;

import java.text.MessageFormat;

/**
 * Exception permettant de retracer une comportement innatendu d'un Controller.
 */
public class ControllerException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public ControllerException(String message, Throwable e) {
        super(message, e);
    }
    
    public ControllerException(String message, Object... arguments) {
        super(MessageFormat.format(message, arguments));
    }
    
}

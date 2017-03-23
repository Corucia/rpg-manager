package com.dedale.frmwrk.action;

import java.util.Objects;
import java.util.function.Supplier;

import com.dedale.frmwrk.view.controller.ControllerException;

import javafx.stage.Stage;

@FunctionalInterface
public interface DialogAction {
    void execute() throws ControllerException;
    
    default DialogAction andThen(DialogAction after) {
        Objects.requireNonNull(after);
        return () -> {
            execute();
            after.execute();
        };
    }
    
    default DialogAction thenClose(Stage s) {
        return andThen(closeDialog(s));
    }
    
    static DialogAction closeDialog(Stage dialog) {
        return dialog::close;
    }
    
    static DialogAction closeDialog(Supplier<Stage> dialogStage) {
        return closeDialog(dialogStage.get());
    }
}
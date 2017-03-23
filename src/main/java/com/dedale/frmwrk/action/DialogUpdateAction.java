package com.dedale.frmwrk.action;

import java.util.function.Supplier;

@FunctionalInterface
public interface DialogUpdateAction<T> {
    
    void update(T bean);
    
    default DialogAction action(Supplier<T> supplier) {
        return () -> update(supplier.get());
    }
    
}

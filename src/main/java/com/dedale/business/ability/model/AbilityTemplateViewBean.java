package com.dedale.business.ability.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modèle d'élement de technologie
 */
public class AbilityTemplateViewBean extends AbilityTemplate {
    
    private AbilityTemplate delegate;
    
    private StringProperty name;
    
    private StringProperty conditions;
    
    private StringProperty description;
    
    public AbilityTemplateViewBean(AbilityTemplate abilityTemplate) {
        this.delegate = abilityTemplate;
        this.name = new SimpleStringProperty(delegate.getName());
        this.conditions = new SimpleStringProperty(delegate.getConditions());
        this.description = new SimpleStringProperty(delegate.getDescription());
    }
    
    public Property<String> name() {
        return name;
    }
    
    public String getName() {
        return name.getValue();
    }
    
    public void setName(String name) {
        delegate.setName(name);
        this.name.setValue(name);
    }
    
    public Property<String> conditions() {
        return conditions;
    }
    
    public String getConditions() {
        return conditions.getValue();
    }
    
    public void setConditions(String conditions) {
        delegate.setConditions(conditions);
        this.conditions.setValue(conditions);
    }
    
    public Property<String> description() {
        return description;
    }
    
    public String getDescription() {
        return description.getValue();
    }
    
    public void setDescription(String description) {
        delegate.setDescription(description);
        this.description.setValue(description);
    }
    
}

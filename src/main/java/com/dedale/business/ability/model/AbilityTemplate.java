package com.dedale.business.ability.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modèle d'élement de technologie
 */
@XmlRootElement
public class AbilityTemplate {
    
    private String name;
    
    private String conditions;
    
    private String description;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getConditions() {
        return conditions;
    }
    
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}

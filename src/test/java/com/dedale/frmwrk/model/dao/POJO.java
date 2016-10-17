package com.dedale.frmwrk.model.dao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class POJO {
    
    private String name;
    
    private String description;
    
    private String conditions;
    
    public POJO() {
    }
    
    public POJO(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getConditions() {
        return conditions;
    }
    
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}

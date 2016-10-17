package com.dedale.business.ability.ihm.view;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.dedale.business.ability.model.AbilityTemplate;

@XmlRootElement(name = "ability-templates")
public class AbilityTemplateListWrapper {
    
    private Collection<AbilityTemplate> abilityTemplateList;
    
    public AbilityTemplateListWrapper() {
        this(new ArrayList<>());
    }
    
    public AbilityTemplateListWrapper(Collection<AbilityTemplate> abilityTemplateList) {
        this.abilityTemplateList = abilityTemplateList;
    }
    
    @XmlElement(name = "abilityTemplate")
    public Collection<AbilityTemplate> getAbilityTemplateList() {
        return abilityTemplateList;
    }
    
    public void setAbilityTemplateList(Collection<AbilityTemplate> abilityTemplateList) {
        this.abilityTemplateList = abilityTemplateList;
    }
    
}

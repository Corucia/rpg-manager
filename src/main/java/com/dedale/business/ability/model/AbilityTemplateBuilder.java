package com.dedale.business.ability.model;

import java.util.function.Consumer;

import com.dedale.frmwrk.model.AbstractFluentBuilder;

/**
 * Constructeur fluide permettant d'assembler un 
 * @param <NB>
 */
public class AbilityTemplateBuilder<NB> extends AbstractFluentBuilder<AbilityTemplate, NB, AbilityTemplateBuilder<NB>> {
    
    public AbilityTemplateBuilder(Consumer<? super AbilityTemplate> beanConsumer, NB nextBuilder) {
        super(defaultTechNodeTemplate(), beanConsumer, nextBuilder);
    }
    
    public AbilityTemplateBuilder() {
        super(defaultTechNodeTemplate());
    }
    
    private static AbilityTemplate defaultTechNodeTemplate() {
        return new AbilityTemplate();
    }
    
    // Méthodes permettant d'assembler l'objet
    
    public AbilityTemplateBuilder<NB> withName(String name) {
        bean.setName(name);
        return this;
    }

    public AbilityTemplateBuilder<NB> withDescription(String description) {
        bean.setDescription(description);
        return this;
    }

    public AbilityTemplateBuilder<NB> withConditions(String conditions) {
        bean.setConditions(conditions);
        return this;
    }
    
    // Méthodes permettant de finaliser la construction
    
    public NB endTechNodeTemplate() {
        return internalEndBean();
    }


}

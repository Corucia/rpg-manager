package com.dedale.frmwrk.model;

import java.util.function.Consumer;

/**
 * Builder permettant d'avoir de la fluidité et de se chaîner les uns les autres.
 * @param <T>
 * @param <NB> nextBuilder : le builder fluide correspondant à l'étape suivante.
 * @param <CB> currentBuilder : le builder fluide correspondant à l'étape courante.
 */
public abstract class AbstractFluentBuilder<T, NB, CB extends AbstractFluentBuilder<T, NB, CB>> {
    
    protected T bean;
    protected Consumer<? super T> beanConsumer;
    protected NB nextBuilder;
    
    @SuppressWarnings("unchecked")
    protected AbstractFluentBuilder(T bean, Consumer<? super T> beanConsumer, NB nextBuilder) {
        this.bean = bean;
        this.beanConsumer = beanConsumer;
        this.nextBuilder = nextBuilder != null ? nextBuilder : (NB) bean;
    }
    
    protected AbstractFluentBuilder(T bean) {
        this(bean, null, null);
    }
    
    // Méthodes permettant de finaliser la construction;
    
    protected void internalBuild() {
        consumeBean();
    }
    
    protected final void consumeBean() {
        if (beanConsumer == null) {
            return;
        }
        beanConsumer.accept(bean);
        beanConsumer = null; // Toute consommation est unique.
    }
    
    /**
     * Termine la construction du bean, renvoie le builder de l'étape suivante.
     * <p>
     * NB : lorsque l'on n'a pas définit d'étape suivante, par exemple pour un builder racine, on considère que le bean est l'étape suivante
     * (et finale).
     */
    protected NB internalEndBean() {
        internalBuild();
        return nextBuilder;
    }
    
    /**
     * Termine la construction du bean, renvoie le bean.
     */
    protected T internalBuildBean() {
        internalBuild();
        return bean;
    }
}
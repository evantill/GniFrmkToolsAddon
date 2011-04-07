package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.BuilderWithJsr303Validation;
import com.gni.frmk.tools.addon.model.component.AbstractComponent;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.component.ComponentConfigurationAdapters.ComponentStatesAdapter;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisited;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

import static com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration.ComponentStateContext.*;
import static com.google.common.collect.Maps.newTreeMap;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 10:21
 *
 * @author: e03229
 */
@XmlRootElement
public abstract class ComponentConfiguration<T extends AbstractComponent<?, S>, S extends AbstractComponentState>
        implements ComponentConfigurationVisited {

    public static enum ComponentStateContext {
        OPEN, CURRENT, CLOSE
    }

    public ComponentConfiguration(Builder<?, ?, T, S> builder) {
        component = builder.component;
        exist = builder.exist;
        selected = builder.selected;
        states = newTreeMap();
        states.put(OPEN, builder.openState);
        states.put(CURRENT, builder.currentState);
        states.put(CLOSE, builder.closeState);
    }

    @Valid
    @XmlElementRef
    private final T component;

    @Valid
    @XmlJavaTypeAdapter(ComponentStatesAdapter.class)
    private final Map<ComponentStateContext, S> states;

    @XmlAttribute
    @NotNull
    private final Boolean exist;

    @XmlAttribute
    @NotNull
    private final Boolean selected;

    protected ComponentConfiguration() {
        component = null;
        states = null;
        exist = null;
        selected = null;
    }

    public T getComponent() {
        return component;
    }

    public Map<ComponentStateContext, S> getStates() {
        return states;
    }

    public static abstract class Builder
            <B extends Builder<B, C, T, S>,
                    C extends ComponentConfiguration<T, S>,
                    T extends AbstractComponent<?, S>,
                    S extends AbstractComponentState>
            extends BuilderWithJsr303Validation<B, C> {
        @NotNull
        private T component;
        @NotNull
        //TODO verifier si peux etre surrime car utile ?
        private S currentState;
        @NotNull
        private S openState;
        @NotNull
        private S closeState;
        @NotNull
        private Boolean selected;
        @NotNull
        private Boolean exist;

        public B defineComponent(T t) {
            component = t;
            currentState = t.getState();
            return self();
        }

        public B select(boolean value) {
            this.selected = value;
            return self();
        }

        public B exist(boolean value) {
            this.exist = value;
            return self();
        }

        public B defineState(ComponentStateContext context, S state) {
            switch (context) {
                case OPEN:
                    return defineOpenState(state);
                case CLOSE:
                    return defineCloseState(state);
                case CURRENT:
                    return defineCurrentState(state);
                default:
                    throw new IllegalStateException(String.format("unknown state context%s", state));
            }
        }

        public B defineCurrentState(S state) {
            currentState = state;
            return self();
        }

        public B defineOpenState(S state) {
            openState = state;
            return self();
        }

        public B defineCloseState(S state) {
            closeState = state;
            return self();
        }
    }
}

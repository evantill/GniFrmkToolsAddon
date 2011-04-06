package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.BuilderWithJsr303Validation;
import com.gni.frmk.tools.addon.model.component.AbstractComponent;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfigurationAdapters.ComponentStatesAdapter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

import static com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration.ComponentStateContext.*;
import static com.google.common.collect.Maps.newTreeMap;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 10:21
 *
 * @author: e03229
 */
@XmlRootElement
public class ComponentConfiguration<C extends AbstractComponent<?, S>, S extends AbstractComponentState> {

    public static enum ComponentStateContext {
        OPEN, CURRENT, CLOSE
    }

    public ComponentConfiguration(Builder<C, S> builder) {
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
    private final C component;

    @Valid
    @XmlJavaTypeAdapter(ComponentStatesAdapter.class)
    private final Map<ComponentStateContext, S> states;

    @XmlAttribute
    @NotNull
    private final Boolean exist;

    @XmlAttribute
    @NotNull
    private final Boolean selected;

    private ComponentConfiguration() {
        component = null;
        states = null;
        exist = null;
        selected = null;
    }

    public C getComponent() {
        return component;
    }

    public Map<ComponentStateContext, S> getStates() {
        return states;
    }

    public static <C extends AbstractComponent<?, S>, S extends AbstractComponentState> Builder<C, S> builder(Class<C> componentType) {
        return new Builder<C, S>() {
            @Override
            public Builder<C, S> self() {
                return this;
            }
        };
    }

    public static abstract class Builder<C extends AbstractComponent<?, S>, S extends AbstractComponentState>
            extends BuilderWithJsr303Validation<Builder<C, S>, ComponentConfiguration<C, S>> {
        @NotNull
        private C component;
        @NotNull
        private S currentState;
        @NotNull
        private S openState;
        @NotNull
        private S closeState;
        @NotNull
        private Boolean selected;
        @NotNull
        private Boolean exist;

        public Builder<C, S> defineComponent(C c) {
            component = c;
            currentState = c.getState();
            return self();
        }

        public Builder<C, S> select(boolean value) {
            this.selected = value;
            return self();
        }

        public Builder<C, S> exist(boolean value) {
            this.exist = value;
            return self();
        }

        public Builder<C, S> defineState(ComponentStateContext context, S state) {
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

        public Builder<C, S> defineCurrentState(S state) {
            currentState = state;
            return self();
        }

        public Builder<C, S> defineOpenState(S state) {
            openState = state;
            return self();
        }

        public Builder<C, S> defineCloseState(S state) {
            closeState = state;
            return self();
        }

        @Override
        protected ComponentConfiguration<C, S> buildObjectBeforeValidation() {
            return new ComponentConfiguration<C, S>(self());
        }
    }
}

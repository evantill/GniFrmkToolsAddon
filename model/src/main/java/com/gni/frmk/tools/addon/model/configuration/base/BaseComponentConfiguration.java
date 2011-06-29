package com.gni.frmk.tools.addon.model.configuration.base;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.base.XmlJaxbAdapters.ComponentStatesAdapter;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:01
 *
 * @author: e03229
 */
@XmlType(propOrder = {"component",
                      "stateConfigurations"})
@XmlRootElement
public class BaseComponentConfiguration
        <T extends ComponentType<T, C, I, S, ?>,
                C extends Component<C, T, I, S, ?>,
                I extends ComponentId<I>,
                S extends ComponentState<S>>
        implements ComponentConfiguration<BaseComponentConfiguration<T, C, I, S>, T, C, I, S> {

    private C component;
    private Map<ComponentStateType, S> stateConfigurations;
    private boolean presentOnIS;

    protected BaseComponentConfiguration() {
        stateConfigurations = Maps.newHashMap();
    }

    public BaseComponentConfiguration(Builder<T, C, I, S> builder) {
        component = builder.component;
        stateConfigurations = builder.stateConfigurations;
        presentOnIS = builder.presentOnIS;
    }

    @XmlElementRef(type = BaseComponent.class)
    public C getComponent() {
        return component;
    }

    public void setComponent(C component) {
        this.component = component;
        setPresentOnIS(component.getCurrentState().exist());
        getStateConfigurations().put(ComponentStateType.CURRENT, component.getCurrentState());
    }

    @Override
    public void updateLastState() {
        Map<ComponentStateType, S> states = getStateConfigurations();
        states.put(ComponentStateType.LAST, states.get(ComponentStateType.CURRENT));
    }

    @Override
    public S getState(ComponentStateType type) {
        return getStateConfigurations().get(type);
    }

    @XmlJavaTypeAdapter(ComponentStatesAdapter.class)
    public Map<ComponentStateType, S> getStateConfigurations() {
        return stateConfigurations;
    }

    public void setStateConfigurations(Map<ComponentStateType, S> stateConfigurations) {
        this.stateConfigurations = stateConfigurations;
    }

    @XmlAttribute(required = true)
    public boolean isPresentOnIS() {
        return presentOnIS;
    }

    public void setPresentOnIS(boolean presentOnIS) {
        this.presentOnIS = presentOnIS;
    }

    @Override
    public T getComponentType() {
        return component.getType();
    }

    @Override
    public int compareTo(BaseComponentConfiguration<T, C, I, S> o) {
        ComparisonChain comparator = ComparisonChain.start()
                                                    .compare(component, o.component)
                                                    .compare(presentOnIS, o.presentOnIS);
        for (ComponentStateType stateType : ComponentStateType.values()) {
            comparator.compare(stateConfigurations.get(stateType), o.stateConfigurations.get(stateType));
        }
        return comparator.result();
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        //TODO implementer visitor pattern
//        getComponent().accept(visitor);
//        visitor.visitComponentConfiguration(this);
    }

    public static <T extends ComponentType<T, C, I, S, ?>,
            C extends Component<C, T, I, S, ?>,
            I extends ComponentId<I>,
            S extends ComponentState<S>>
    Builder<T, C, I, S> builder(T type) {
        return new Builder<T, C, I, S>();
    }

    public static class Builder
            <T extends ComponentType<T, C, I, S, ?>,
                    C extends Component<C, T, I, S, ?>,
                    I extends ComponentId<I>,
                    S extends ComponentState<S>>
            implements BuilderWithValidation<Builder<T, C, I, S>, BaseComponentConfiguration<T, C, I, S>> {

        private C component;
        private Map<ComponentStateType, S> stateConfigurations = Maps.newHashMap();
        private boolean presentOnIS;

        public Builder<T, C, I, S> fromComponent(C value) {
            S currentState = value.getCurrentState();
            return component(value)
                    .state(ComponentStateType.CURRENT, currentState)
                    .state(ComponentStateType.OPEN, currentState.getOpenState())
                    .state(ComponentStateType.CLOSE, currentState.getCloseState())
                    .presentOnIS(currentState.exist());
        }

        public Builder<T, C, I, S> component(C value) {
            component = checkNotNull(value);
            return this;
        }

        public Builder<T, C, I, S> presentOnIS(boolean value) {
            presentOnIS = value;
            return this;
        }

        public Builder<T, C, I, S> state(ComponentStateType type, S value) {
            stateConfigurations.put(checkNotNull(type), checkNotNull(value));
            return this;
        }

        @Override
        public BaseComponentConfiguration<T, C, I, S> build() {
            return new BaseComponentConfiguration<T, C, I, S>(this);
        }

        @Override
        public Builder<T, C, I, S> validate() {
            checkNotNull(component);
            checkNotNull(stateConfigurations);
            checkNotNull(presentOnIS);
            return this;
        }

        @Override
        public Builder<T, C, I, S> self() {
            return this;
        }
    }
}


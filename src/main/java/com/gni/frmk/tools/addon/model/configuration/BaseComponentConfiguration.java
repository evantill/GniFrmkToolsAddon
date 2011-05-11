package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.Component;
import com.gni.frmk.tools.addon.model.Component.State;
import com.gni.frmk.tools.addon.model.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.configuration.XmlJaxbAdapters.ComponentStatesAdapter;
import com.gni.frmk.tools.addon.visitor.ConfigurationVisitor;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:01
 *
 * @author: e03229
 */
public class BaseComponentConfiguration<C extends Component<?, S, ?>, S extends Component.State>
        implements ComponentConfiguration<C, S> {
    private C component;
    private Map<Component.StateType, S> stateConfigurations;
    private boolean presentOnIS;

    public BaseComponentConfiguration() {
        stateConfigurations = Maps.newHashMap();
    }

    @XmlElement(required = true, type = BaseComponent.class)
    public C getComponent() {
        return component;
    }

    public void setComponent(C component) {
        this.component = component;
    }

    @XmlJavaTypeAdapter(ComponentStatesAdapter.class)
    public Map<Component.StateType, S> getStateConfigurations() {
        return stateConfigurations;
    }

    public void setStateConfigurations(Map<Component.StateType, S> stateConfigurations) {
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
    public void accept(ConfigurationVisitor visitor) {
        getComponent().accept(visitor);
        visitor.visitComponentConfiguration(this);
    }

    //TODO ne marche pas mais pourquoi ?
    public static <CC extends Component<?,SS,?>,SS extends State> BaseComponentConfiguration<CC, SS> newComponentConfiguration() {
        return new BaseComponentConfiguration<CC,SS>();
    }

    //TODO ne marche pas mais pourquoi ?
    public static <CC extends BaseComponent<?, SS, ?>, SS extends Component.State> BaseComponentConfiguration<CC, SS>
    newComponentConfiguration(CC component, Map<Component.StateType, SS> states, boolean presentOnIS) {
        BaseComponentConfiguration<CC, SS> result = new BaseComponentConfiguration<CC, SS>();
        result.setComponent(component);
        result.setStateConfigurations(states);
        result.setPresentOnIS(presentOnIS);
        return result;
    }
}


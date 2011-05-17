package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.configuration.XmlJaxbAdapters.ComponentStatesAdapter;
import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.visitor.ConfigurationVisitor;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:01
 *
 * @author: e03229
 */
@XmlType(propOrder = {"component",
                      "stateConfigurations"})
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


}


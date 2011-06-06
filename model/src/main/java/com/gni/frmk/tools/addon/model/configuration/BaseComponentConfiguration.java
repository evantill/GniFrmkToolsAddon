package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.gni.frmk.tools.addon.model.configuration.XmlJaxbAdapters.ComponentStatesAdapter;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitor;
import com.google.common.collect.Maps;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement
public class BaseComponentConfiguration
        <C extends BaseComponent<C, ?, ?, S, ?>,
                S extends BaseComponentState>
        implements ComponentConfiguration<C, S> {
    private C component;
    private Map<ComponentStateType, S> stateConfigurations;
    private boolean presentOnIS;

    public BaseComponentConfiguration() {
        stateConfigurations = Maps.newHashMap();
    }

    @XmlElementRef(type = BaseComponent.class)
    public C getComponent() {
        return component;
    }

    public void setComponent(C component) {
        this.component = component;
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
    public void accept(ConfigurationVisitor visitor) {
        getComponent().accept(visitor);
        visitor.visitComponentConfiguration(this);
    }


}


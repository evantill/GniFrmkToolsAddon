package com.gni.frmk.tools.addon.data3.configuration;

import com.gni.frmk.tools.addon.data3.components.Component;
import com.gni.frmk.tools.addon.data3.components.ComponentId;
import com.gni.frmk.tools.addon.data3.components.ComponentInformation;
import com.gni.frmk.tools.addon.data3.components.ComponentType;
import com.gni.frmk.tools.addon.data3.configuration.ComponentDescriptionAdapters.InformationsAdapter;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ComponentDescription {

    @XmlElement(required = true)
    private final String type;
    @XmlElement(required = true)
    private final String id;

    @XmlElement
    @XmlJavaTypeAdapter(InformationsAdapter.class)
    private final Map<String, String> informations;

    public ComponentDescription(Builder builder) {
        type = builder.type;
        id = builder.id;
        informations = Maps.newHashMap(builder.informations);
    }

    /**
     * Empty constructor used by jaxb.
     */
    private ComponentDescription() {
        type = null;
        id = null;
        informations = null;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getInformations() {
        return Collections.unmodifiableMap(informations);
    }

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private String id;
        private HashMap<String, String> informations = newHashMap();

        Builder defineType(ComponentType value) {
            type = value.name();
            return this;
        }

        Builder defineId(ComponentId value) {
            id = value.asString();
            return this;
        }

        Builder defineInformation(ComponentInformation value) {
            informations.put(value.getKey(), value.getValue());
            return this;
        }

        Builder defineInformations(Collection<ComponentInformation> informations) {
            for (ComponentInformation info : informations) {
                defineInformation(info);
            }
            return this;
        }

        public Builder define(Component component) {
            return defineType(component.getType()).defineId(component.getId())
                    .defineInformations(component.getInformations());
        }

        ComponentDescription build() {
            return new ComponentDescription(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComponentDescription that = (ComponentDescription) o;

        if (!id.equals(that.id))
            return false;
        if (!informations.equals(that.informations))
            return false;
        if (!type.equals(that.type))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + informations.hashCode();
        return result;
    }
}

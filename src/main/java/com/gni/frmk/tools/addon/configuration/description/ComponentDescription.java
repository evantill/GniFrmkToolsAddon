package com.gni.frmk.tools.addon.configuration.description;

import com.gni.frmk.tools.addon.configuration.BuilderWithValidation;
import com.gni.frmk.tools.addon.configuration.components.Component;
import com.gni.frmk.tools.addon.configuration.components.ComponentDetail;
import com.gni.frmk.tools.addon.configuration.components.ComponentId;
import com.gni.frmk.tools.addon.configuration.components.ComponentType;
import com.gni.frmk.tools.addon.configuration.description.ComponentDescriptionAdapters.InformationsAdapter;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;
import com.gni.frmk.tools.addon.configuration.visitors.GenericComponentVisitor;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "component")
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

    /**
     * ComponentDescription is immutable
     *
     * @param source the description to clone
     * @return the same object as this class is immutable
     */
    public static ComponentDescription from(ComponentDescription source) {
        return source;
    }

    public static class Builder extends GenericComponentVisitor implements BuilderWithValidation<ComponentDescription>, ComponentVisitor {
        private static final String COMPONENT_TYPE_REQUIRED = "component type required";
        private static final String COMPONENT_ID_REQUIRED = "component id required";
        private String type;
        private String id;
        private HashMap<String, String> informations = newHashMap();

        Builder componentType(ComponentType value) {
            type = checkNotNull(value, COMPONENT_TYPE_REQUIRED).name();
            return self();
        }

        Builder componentId(ComponentId value) {
            id = checkNotNull(value, COMPONENT_ID_REQUIRED).asString();
            return self();
        }

        Builder addInformation(ComponentDetail value) {
            informations.put(value.getKey(), value.getValue().asString());
            return this;
        }

        Builder addAllInformations(List<? extends ComponentDetail> informations) {
            for (ComponentDetail info : informations) {
                addInformation(info);
            }
            return this;
        }

        public Builder from(Component component) {
            component.accept(this);
            return self();
        }

        @Override
        public ComponentDescription build() throws BuildException {
            return new ComponentDescription(this);
        }

        @Override
        public void validate() throws ValidationException {
            try {
                checkNotNull(type, COMPONENT_TYPE_REQUIRED);
                checkNotNull(id, COMPONENT_ID_REQUIRED);
            } catch (NullPointerException npex) {
                throw new ValidationException(npex);
            }
        }

        @Override
        public ComponentDescription buildAndValidate() throws BuildException, ValidationException {
            ComponentDescription desc = build();
            //TODO validate
            validate();
            return desc;
        }

        @Override
        public void visitAny(Component visited) {
            componentType(visited.getType());
            componentId(visited.getId());
            addAllInformations(visited.getDetails());
        }

        private Builder self() {
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof ComponentDescription) {
            ComponentDescription other = (ComponentDescription) o;
            return Objects.equal(getType(), other.getType()) &&
                   Objects.equal(getId(), other.getId()) &&
                   Objects.equal(getInformations(), other.getInformations());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, id, informations);
    }

}

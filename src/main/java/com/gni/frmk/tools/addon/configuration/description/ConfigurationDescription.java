package com.gni.frmk.tools.addon.configuration.description;

import com.gni.frmk.tools.addon.configuration.components.Component;
import com.gni.frmk.tools.addon.configuration.BuilderWithValidation;
import com.google.common.base.Objects;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.NONE)
public class ConfigurationDescription {

    @XmlAttribute(required = true)
    private final String name;

    @XmlAttribute(required = true)
    private final Date creation;

    @XmlAttribute
    private final Date modification;

    @XmlElement(name = "component")
    @XmlElementWrapper
    private final List<ComponentDescription> components;

    private ConfigurationDescription() {
        name = null;
        creation = null;
        modification = null;
        components = null;
    }

    public ConfigurationDescription(Builder builder) {
        name = builder.configurationName;
        creation = builder.creationDate;
        modification = builder.modificationDate;
        components = Collections.unmodifiableList(builder.components);
    }

    public String getName() {
        return name;
    }

    public Date getCreation() {
        return new Date(creation.getTime());
    }

    public Date getModification() {
        return new Date(modification.getTime());
    }

    public List<ComponentDescription> getComponents() {
        return Collections.unmodifiableList(components);
    }

    public static Builder build() {
        return new Builder();
    }

    /**
     * Configuration is immutable
     *
     * @param source the configuration to clone
     * @return the same object as this class is immutable
     */
    public static ConfigurationDescription from(ConfigurationDescription source) {
        return source;
    }

    public static class Builder implements BuilderWithValidation<ConfigurationDescription> {

        private static final String CONFIGURATION_NAME_REQUIRED = "configuration name required";
        private static final String CREATION_DATE_REQUIRED = "creation date required";
        private static final String MODIFICATION_DATE_REQUIRED = "modification date required";
        private String configurationName;
        private Date creationDate;
        private Date modificationDate;
        private List<ComponentDescription> components = newArrayList();

        Builder from(ConfigurationDescription source) {
            configurationName = source.getName();
            creationDate = source.getCreation();
            modificationDate = source.getModification();
            components.clear();
            for (ComponentDescription description : source.getComponents()) {
                components.add(ComponentDescription.from(description));
            }
            return self();
        }

        Builder name(String value) {
            configurationName = checkNotNull(value, CONFIGURATION_NAME_REQUIRED);
            return self();
        }

        Builder createAt(Date value) {
            creationDate = checkNotNull(value, CREATION_DATE_REQUIRED);
            return self();
        }

        Builder modifyAt(Date value) {
            modificationDate = checkNotNull(value, MODIFICATION_DATE_REQUIRED);
            return self();
        }

        private Builder self() {
            return this;
        }

        public Builder addComponent(Component component) {
            components.add(ComponentDescription.build().from(component).build());
            return this;
        }

        @Override
        public ConfigurationDescription build() throws BuildException {
            return new ConfigurationDescription(this);
        }

        @Override
        public void validate() throws ValidationException {
            try {
                checkNotNull(configurationName, CONFIGURATION_NAME_REQUIRED);
                checkNotNull(creationDate, CREATION_DATE_REQUIRED);
                checkNotNull(modificationDate, MODIFICATION_DATE_REQUIRED);
            } catch (NullPointerException npex) {
                throw new ValidationException(npex);
            }
        }

        @Override
        public ConfigurationDescription buildAndValidate() throws BuildException, ValidationException {
            ConfigurationDescription desc = build();
            //TODO validate
            validate();
            return desc;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof ConfigurationDescription) {
            ConfigurationDescription other = (ConfigurationDescription) o;
            return Objects.equal(getName(), other.getName()) &&
                   Objects.equal(getCreation(), other.getCreation()) &&
                   Objects.equal(getModification(), other.getModification());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + creation.hashCode();
        result = 31 * result + (modification != null ? modification.hashCode() : 0);
        return result;
    }
}

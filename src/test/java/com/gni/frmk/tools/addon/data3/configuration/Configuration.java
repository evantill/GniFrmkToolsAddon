package com.gni.frmk.tools.addon.data3.configuration;

import com.gni.frmk.tools.addon.data3.components.Component;
import com.google.common.base.Preconditions;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Configuration {

    @XmlAttribute(required = true)
    private final String name;

    @XmlAttribute(required = true)
    private final Date creation;

    @XmlAttribute
    private final Date modification;

    @XmlElement(name = "component")
    @XmlElementWrapper
    private final List<ComponentDescription> components;

    private Configuration() {
        name = null;
        creation = null;
        modification = null;
        components = null;
    }

    public Configuration(Builder builder) {
        name = builder.name;
        creation = builder.creation;
        modification = builder.modification;
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

    public static class Builder {
        private String name;
        private Date creation;
        private Date modification;
        private List<ComponentDescription> components = newArrayList();

        public Builder name(String value) {
            name = Preconditions.checkNotNull(value, "name must not be null");
            return this;
        }

        public Builder create(Date when) {
            creation = Preconditions.checkNotNull(when, "creation date must not be null");
            return this;
        }

        public Builder create() {
            return create(new Date());
        }

        public Builder modify(Date when) {
            modification = Preconditions.checkNotNull(when, "modification date must not be null");
            return this;
        }

        public Builder modify() {
            return modify(new Date());
        }

        public Builder addComponent(Component component) {
            components.add(ComponentDescription.build().define(component).build());
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Configuration that = (Configuration) o;

        if (!creation.equals(that.creation))
            return false;
        if (modification != null ? !modification.equals(that.modification) : that.modification != null)
            return false;
        if (!name.equals(that.name))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + creation.hashCode();
        result = 31 * result + (modification != null ? modification.hashCode() : 0);
        return result;
    }
}

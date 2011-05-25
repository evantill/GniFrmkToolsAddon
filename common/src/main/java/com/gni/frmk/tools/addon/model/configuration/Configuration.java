package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:02
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {"id",
                      "name",
                      "creation",
                      "modification",
                      "componentConfigurations"})
public class Configuration implements Iterable<ComponentConfiguration> {

    private ConfigurationId id;
    private String name;
    private Date creation;
    private Date modification;

    private List<ComponentConfiguration> componentConfigurations;

    public Configuration() {
        componentConfigurations = Lists.newArrayList();
    }

    @XmlElement(required = true, name = "configurationId")
    public ConfigurationId getId() {
        return id;
    }

    public void setId(ConfigurationId id) {
        this.id = id;
    }

    @XmlAttribute(required = true)
    public String getName() {
        return name;
    }

    @XmlAttribute(required = true)
    public Date getCreation() {
        return creation;
    }

    @XmlAttribute(required = true)
    public Date getModification() {
        return modification;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "componentConfigurations", required = true)
    @XmlElement(name = "componentConfiguration", type = BaseComponentConfiguration.class)
    public List<ComponentConfiguration> getComponentConfigurations() {
        return componentConfigurations;
    }

    public void setComponentConfigurations(List<ComponentConfiguration> componentConfigurations) {
        this.componentConfigurations = componentConfigurations;
    }

    @Override
    public Iterator<ComponentConfiguration> iterator() {
        return componentConfigurations.iterator();
    }

    public Set<Type> listTypes() {
        Set<Type> types = Sets.newHashSet();
        for (ComponentConfiguration c : this) {
            types.add(c.getComponent().getType());
        }
        return types;
    }

    @XmlTransient
    private static final class ComponentTypeFilter implements Predicate<ComponentConfiguration> {

        private final Type selectedType;

        private ComponentTypeFilter(Type selectedType) {
            this.selectedType = checkNotNull(selectedType);
        }

        @Override
        public boolean apply(ComponentConfiguration input) {
            if (input == null || input.getComponent() == null) {
                return false;

            } else {
                Type inputType = input.getComponent().getType();
                return selectedType == inputType;
            }
        }

        //TODO use an enum for each type to avoid object creations
        static final ComponentTypeFilter filterOn(Type selectedType) {
            return new ComponentTypeFilter(selectedType);
        }
    }

    public Collection<ComponentConfiguration> listComponentConfigurationsByType(Type type) {
        return Collections2.filter(componentConfigurations, ComponentTypeFilter.filterOn(type));
    }

    public void accept(ConfigurationVisitor visitor) {
        for (ComponentConfiguration<?, ?> c : this) {
            c.accept(visitor);
        }
        visitor.visitConfiguration(this);
    }
}

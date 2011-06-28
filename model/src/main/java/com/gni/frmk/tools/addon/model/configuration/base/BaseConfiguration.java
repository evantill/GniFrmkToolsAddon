package com.gni.frmk.tools.addon.model.configuration.base;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationInfo;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Sets;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Collections2.filter;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 11:24
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {"id",
                      "info",
                      "internalComponentConfigurations"})
public class BaseConfiguration
        implements Configuration<BaseConfiguration>, Iterable<ComponentConfiguration<?, ?, ?, ?>> {

    private ConfigurationId<?> id;
    private ConfigurationInfo<?> info;
    private SortedSet<ComponentConfiguration<?, ?, ?, ?>> componentConfigurations;

    private BaseConfiguration() {
        componentConfigurations = Sets.newTreeSet();
    }

    private BaseConfiguration(Builder builder) {
        id = builder.id;
        info = builder.info;
        componentConfigurations = builder.componentConfigurations;
    }

    @Override
    @XmlElementRef(type = BaseConfigurationId.class)
    public ConfigurationId<?> getId() {
        return id;
    }

    private void setId(ConfigurationId<?> id) {
        this.id = id;
    }

    @XmlElementRef(type = BaseConfigurationInformation.class)
    @Override
    public ConfigurationInfo<?> getInfo() {
        return info;
    }

    private void setInfo(ConfigurationInfo<?> info) {
        this.info = info;
    }

    @XmlElementWrapper(name = "componentConfigurations", required = true)
    @XmlElementRef(name = "componentConfiguration", type = BaseComponentConfiguration.class)
    private Set<ComponentConfiguration<?, ?, ?, ?>> getInternalComponentConfigurations() {
        return componentConfigurations;
    }

    @Override
    public Set<ComponentConfiguration<?, ?, ?, ?>> getComponentConfigurations() {
        return Collections.unmodifiableSet(componentConfigurations);
    }

    private void setComponentConfigurations(SortedSet<ComponentConfiguration<?, ?, ?, ?>> componentConfigurations) {
        this.componentConfigurations = componentConfigurations;
    }

    @Override
    public Set<ComponentType<?, ?, ?, ?, ?>> getComponentConfigurationTypes() {
        Set<ComponentType<?, ?, ?, ?, ?>> types = Sets.newHashSet();
        for (ComponentConfiguration<?, ?, ?, ?> componentConfiguration : componentConfigurations) {
            types.add(componentConfiguration.getComponentType());
        }
        return types;
    }

    @XmlTransient
    private static final class ComponentTypeFilter implements Predicate<ComponentConfiguration<?, ?, ?, ?>> {

        private final ComponentType<?, ?, ?, ?, ?> selectedType;

        private ComponentTypeFilter(ComponentType selectedType) {
            this.selectedType = checkNotNull(selectedType);
        }

        @Override
        public boolean apply(ComponentConfiguration<?, ?, ?, ?> input) {
            if (input == null || input.getComponent() == null) {
                return false;
            } else {
                ComponentType<?, ?, ?, ?, ?> inputType = input.getComponent().getType();
                return selectedType.getTypeComponent().equals(inputType.getTypeComponent());
            }
        }

        //TODO use an enum for each type to avoid object creations
        static final ComponentTypeFilter filterOn(ComponentType<?, ?, ?, ?, ?> selectedType) {
            return new ComponentTypeFilter(selectedType);
        }

    }

    @Override
    public Set<ComponentConfiguration<?, ?, ?, ?>> getComponentConfigurationsByType(ComponentType<?, ?, ?, ?, ?> type) {
        Set<ComponentConfiguration<?, ?, ?, ?>> selected = Sets.newHashSet();
        Collection<ComponentConfiguration<?, ?, ?, ?>> filtered = filter(componentConfigurations, ComponentTypeFilter.filterOn(type));
        for (ComponentConfiguration<?, ?, ?, ?> element : filtered) {
            selected.add(element);
        }
        return selected;
    }

    @Override
    public Iterator<ComponentConfiguration<?, ?, ?, ?>> iterator() {
        return componentConfigurations.iterator();
    }

    public void accept(ConfigurationVisitor visitor) {
        //TODO implementer visitor pattern
//        for (ComponentConfiguration<?, ?> c : this) {
//            c.accept(visitor);
//        }
//        visitor.visitConfiguration(this);
    }

    @Override
    public int compareTo(BaseConfiguration o) {
        return ComparisonChain.start()
                              .compare(id, o.id)
                              .compare(info, o.info)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseConfiguration that = (BaseConfiguration) o;
        return Objects.equal(id, that.id)
               && Objects.equal(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, info);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static class Builder
            implements BuilderWithValidation<Builder, BaseConfiguration> {

        private ConfigurationId<?> id;
        private ConfigurationInfo<?> info;
        private final SortedSet<ComponentConfiguration<?, ?, ?, ?>> componentConfigurations = Sets.newTreeSet();

        public Builder id(ConfigurationId<?> value) {
            id = checkNotNull(value);
            return this;
        }

        public Builder info(ConfigurationInfo<?> value) {
            info = checkNotNull(value);
            return this;
        }

        public Builder addComponentConfiguration(ComponentConfiguration<?, ?, ?, ?> value) {
            componentConfigurations.add(checkNotNull(value));
            return this;
        }

        public Builder addAllComponentConfiguration(Collection<ComponentConfiguration<?, ?, ?, ?>> values) {
            for (ComponentConfiguration<?, ?, ?, ?> value : values) {
                addComponentConfiguration(value);
            }
            return this;
        }

        @Override
        public BaseConfiguration build() {
            return new BaseConfiguration(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(id);
            checkNotNull(info);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
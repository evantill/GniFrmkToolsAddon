package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.immutable.ImmutableObject;
import com.gni.frmk.tools.addon.model.api.immutable.MutableObject;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration.MutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 18:15
 *
 * @author: e03229
 */
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.NONE)
public class ImmutableConfiguration
        implements ImmutableObject<ImmutableConfiguration, ImmutableConfiguration, MutableConfiguration>,
        ReadableConfiguration {

    private final String id;
    private final String name;
    private final String packageName;
    private final Date creation;
    private final Date modification;
    private final List<AdapterConnectionConfiguration> adapterConnectionConfigurations;
    private final List<AdapterListenerConfiguration> adapterListenerConfigurations;
    private final List<AdapterNotificationConfiguration> adapterNotificationConfigurations;
    private final List<IntegrationServerPackageConfiguration> integrationServerPackageConfigurations;
    private final List<JmsAliasConfiguration> jmsAliasConfigurations;
    private final List<JmsTriggerConfiguration> jmsTriggerConfigurations;
    private final List<NativeTriggerConfiguration> nativeTriggerConfigurations;
    private final List<PortConfiguration> portConfigurations;
    private final List<SchedulerConfiguration> schedulerConfigurations;

    public ImmutableConfiguration(ReadableConfiguration source) {
        id = source.getId();
        name = source.getName();
        packageName = source.getPackageName();
        creation = source.getCreation();
        modification = source.getModification();
        adapterConnectionConfigurations = Lists.newArrayList();
        adapterConnectionConfigurations.addAll(source.getAdapterConnectionConfigurations());
        adapterListenerConfigurations = Lists.newArrayList();
        adapterListenerConfigurations.addAll(source.getAdapterListenerConfigurations());
        adapterNotificationConfigurations = Lists.newArrayList();
        adapterNotificationConfigurations.addAll(source.getAdapterNotificationConfigurations());
        integrationServerPackageConfigurations = Lists.newArrayList();
        integrationServerPackageConfigurations.addAll(source.getIntegrationServerPackageConfigurations());
        jmsAliasConfigurations = Lists.newArrayList();
        jmsAliasConfigurations.addAll(source.getJmsAliasConfigurations());
        jmsTriggerConfigurations = Lists.newArrayList();
        jmsTriggerConfigurations.addAll(source.getJmsTriggerConfigurations());
        nativeTriggerConfigurations = Lists.newArrayList();
        nativeTriggerConfigurations.addAll(source.getNativeTriggerConfigurations());
        portConfigurations = Lists.newArrayList();
        portConfigurations.addAll(source.getPortConfigurations());
        schedulerConfigurations = Lists.newArrayList();
        schedulerConfigurations.addAll(source.getSchedulerConfigurations());
    }

    @Override
    public ImmutableConfiguration toImmutable() {
        return this;
    }

    @Override
    public MutableConfiguration toMutable() {
        return new MutableConfiguration(this);
    }

    public List<AdapterConnectionConfiguration> getAdapterConnectionConfigurations() {
        return unmodifiableList(adapterConnectionConfigurations);
    }

    public List<AdapterListenerConfiguration> getAdapterListenerConfigurations() {
        return unmodifiableList(adapterListenerConfigurations);
    }

    public List<AdapterNotificationConfiguration> getAdapterNotificationConfigurations() {
        return unmodifiableList(adapterNotificationConfigurations);
    }

    public List<IntegrationServerPackageConfiguration> getIntegrationServerPackageConfigurations() {
        return unmodifiableList(integrationServerPackageConfigurations);
    }

    public List<JmsAliasConfiguration> getJmsAliasConfigurations() {
        return unmodifiableList(jmsAliasConfigurations);
    }

    public List<JmsTriggerConfiguration> getJmsTriggerConfigurations() {
        return unmodifiableList(jmsTriggerConfigurations);
    }

    public List<NativeTriggerConfiguration> getNativeTriggerConfigurations() {
        return unmodifiableList(nativeTriggerConfigurations);
    }

    public List<PortConfiguration> getPortConfigurations() {
        return unmodifiableList(portConfigurations);
    }

    public List<SchedulerConfiguration> getSchedulerConfigurations() {
        return unmodifiableList(schedulerConfigurations);
    }

    public List<ComponentConfiguration<?, ?>> getComponentConfigurations() {
        List<ComponentConfiguration<?, ?>> union = Lists.newArrayList();
        union.addAll(getAdapterConnectionConfigurations());
        union.addAll(getAdapterListenerConfigurations());
        union.addAll(getAdapterNotificationConfigurations());
        union.addAll(getIntegrationServerPackageConfigurations());
        union.addAll(getJmsAliasConfigurations());
        union.addAll(getJmsTriggerConfigurations());
        union.addAll(getNativeTriggerConfigurations());
        union.addAll(getPortConfigurations());
        union.addAll(getSchedulerConfigurations());
        return union;
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitConfiguration(this);
        for (ComponentConfiguration<?, ?> visited : getComponentConfigurations()) {
            visited.accept(visitor);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreation() {
        return new Date(creation.getTime());
    }

    public String getPackageName() {
        return packageName;
    }

    public Date getModification() {
        return new Date(modification.getTime());
    }

    public static class MutableConfiguration
            implements MutableObject<ImmutableConfiguration, ImmutableConfiguration, MutableConfiguration>,
            WritableConfiguration {
        private String id;
        private String name;
        private String packageName;
        private Date creation;
        private Date modification;
        private List<AdapterConnectionConfiguration> adapterConnectionConfigurations = newArrayList();
        private List<AdapterListenerConfiguration> adapterListenerConfigurations = newArrayList();
        private List<AdapterNotificationConfiguration> adapterNotificationConfigurations = newArrayList();
        private List<IntegrationServerPackageConfiguration> integrationServerPackageConfigurations = newArrayList();
        private List<JmsAliasConfiguration> jmsAliasConfigurations = newArrayList();
        private List<JmsTriggerConfiguration> jmsTriggerConfigurations = newArrayList();
        private List<NativeTriggerConfiguration> nativeTriggerConfigurations = newArrayList();
        private List<PortConfiguration> portConfigurations = newArrayList();
        private List<SchedulerConfiguration> schedulerConfigurations = newArrayList();

        public MutableConfiguration() {
        }

        public MutableConfiguration(ReadableConfiguration source) {
            id = source.getId();
            name = source.getName();
            packageName = source.getPackageName();
            creation = source.getCreation();
            modification = source.getModification();
            adapterConnectionConfigurations = Lists.newArrayList();
            adapterConnectionConfigurations.addAll(source.getAdapterConnectionConfigurations());
            adapterListenerConfigurations = Lists.newArrayList();
            adapterListenerConfigurations.addAll(source.getAdapterListenerConfigurations());
            adapterNotificationConfigurations = Lists.newArrayList();
            adapterNotificationConfigurations.addAll(source.getAdapterNotificationConfigurations());
            integrationServerPackageConfigurations = Lists.newArrayList();
            integrationServerPackageConfigurations.addAll(source.getIntegrationServerPackageConfigurations());
            jmsAliasConfigurations = Lists.newArrayList();
            jmsAliasConfigurations.addAll(source.getJmsAliasConfigurations());
            jmsTriggerConfigurations = Lists.newArrayList();
            jmsTriggerConfigurations.addAll(source.getJmsTriggerConfigurations());
            nativeTriggerConfigurations = Lists.newArrayList();
            nativeTriggerConfigurations.addAll(source.getNativeTriggerConfigurations());
            portConfigurations = Lists.newArrayList();
            portConfigurations.addAll(source.getPortConfigurations());
            schedulerConfigurations = Lists.newArrayList();
            schedulerConfigurations.addAll(source.getSchedulerConfigurations());
        }

        @Override
        public ImmutableConfiguration toImmutable() {
            return new ImmutableConfiguration(this);
        }

        @Override
        public MutableConfiguration toMutable() {
            return new MutableConfiguration(this);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitConfiguration(toImmutable());
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public void setCreation(Date creation) {
            this.creation = new Date(creation.getTime());
        }

        public void setModification(Date modification) {
            this.modification = new Date(modification.getTime());
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPackageName() {
            return packageName;
        }

        public Date getCreation() {
            return new Date(creation.getTime());
        }

        public Date getModification() {
            return new Date(modification.getTime());
        }

        public void addAdapterConnectionConfiguration(AdapterConnectionConfiguration connection) {
            adapterConnectionConfigurations.add(connection);
        }

        public void addAdapterListenerConfiguration(AdapterListenerConfiguration listener) {
            adapterListenerConfigurations.add(listener);
        }

        public void addAdapterNotificationConfiguration(AdapterNotificationConfiguration notification) {
            adapterNotificationConfigurations.add(notification);
        }

        public void addIntegrationServerPackageConfiguration(IntegrationServerPackageConfiguration pkg) {
            integrationServerPackageConfigurations.add(pkg);
        }

        public void addJmsAliasConfiguration(JmsAliasConfiguration alias) {
            jmsAliasConfigurations.add(alias);
        }

        public void addJmsTriggerConfiguration(JmsTriggerConfiguration trigger) {
            jmsTriggerConfigurations.add(trigger);
        }

        public void addNativeTriggerConfiguration(NativeTriggerConfiguration trigger) {
            nativeTriggerConfigurations.add(trigger);
        }

        public void addPortConfiguration(PortConfiguration port) {
            portConfigurations.add(port);
        }

        public void addSchedulerConfiguration(SchedulerConfiguration scheduler) {
            schedulerConfigurations.add(scheduler);
        }

        public void clear() {
            adapterConnectionConfigurations.clear();
            adapterListenerConfigurations.clear();
            adapterNotificationConfigurations.clear();
            integrationServerPackageConfigurations.clear();
            jmsAliasConfigurations.clear();
            jmsTriggerConfigurations.clear();
            nativeTriggerConfigurations.clear();
            portConfigurations.clear();
            schedulerConfigurations.clear();
        }

        public List<AdapterConnectionConfiguration> getAdapterConnectionConfigurations() {
            return adapterConnectionConfigurations;
        }

        public List<AdapterListenerConfiguration> getAdapterListenerConfigurations() {
            return adapterListenerConfigurations;
        }

        public List<AdapterNotificationConfiguration> getAdapterNotificationConfigurations() {
            return adapterNotificationConfigurations;
        }

        public List<IntegrationServerPackageConfiguration> getIntegrationServerPackageConfigurations() {
            return integrationServerPackageConfigurations;
        }

        public List<JmsAliasConfiguration> getJmsAliasConfigurations() {
            return jmsAliasConfigurations;
        }

        public List<JmsTriggerConfiguration> getJmsTriggerConfigurations() {
            return jmsTriggerConfigurations;
        }

        public List<NativeTriggerConfiguration> getNativeTriggerConfigurations() {
            return nativeTriggerConfigurations;
        }

        public List<PortConfiguration> getPortConfigurations() {
            return portConfigurations;
        }

        public List<SchedulerConfiguration> getSchedulerConfigurations() {
            return schedulerConfigurations;
        }

        @Override
        public void setAdapterConnectionConfiguration(List<AdapterConnectionConfiguration> elements) {
            adapterConnectionConfigurations.clear();
            adapterConnectionConfigurations.addAll(elements);
        }

        @Override
        public void setAdapterListenerConfiguration(List<AdapterListenerConfiguration> elements) {
            adapterListenerConfigurations.clear();
            adapterListenerConfigurations.addAll(elements);
        }

        @Override
        public void setAdapterNotificationConfiguration(List<AdapterNotificationConfiguration> elements) {
            adapterNotificationConfigurations.clear();
            adapterNotificationConfigurations.addAll(elements);
        }

        @Override
        public void setIntegrationServerPackageConfiguration(List<IntegrationServerPackageConfiguration> elements) {
            integrationServerPackageConfigurations.clear();
            integrationServerPackageConfigurations.addAll(elements);
        }

        @Override
        public void setJmsAliasConfiguration(List<JmsAliasConfiguration> elements) {
            jmsAliasConfigurations.clear();
            jmsAliasConfigurations.addAll(elements);
        }

        @Override
        public void setJmsTriggerConfiguration(List<JmsTriggerConfiguration> elements) {
            jmsTriggerConfigurations.clear();
            jmsTriggerConfigurations.addAll(elements);
        }

        @Override
        public void setNativeTriggerConfiguration(List<NativeTriggerConfiguration> elements) {
            nativeTriggerConfigurations.clear();
            nativeTriggerConfigurations.addAll(elements);
        }

        @Override
        public void setPortConfiguration(List<PortConfiguration> elements) {
            portConfigurations.clear();
            portConfigurations.addAll(elements);
        }

        @Override
        public void setSchedulerConfiguration(List<SchedulerConfiguration> elements) {
            schedulerConfigurations.clear();
            schedulerConfigurations.addAll(elements);
        }
    }
}

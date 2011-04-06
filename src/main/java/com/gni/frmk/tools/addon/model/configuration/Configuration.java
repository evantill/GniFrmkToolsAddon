package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.BuilderWithJsr303Validation;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationVisited;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationVisitor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
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
public class Configuration implements ConfigurationVisited{
    @NotNull
    @Pattern(regexp="\\w{1,32}")
    @XmlAttribute
    private final String id;
    @NotNull
    @XmlAttribute
    private final String name;
    @NotNull
    @XmlAttribute
    private final String packageName;
    @NotNull
    @XmlAttribute
    private final Date creation;
    @NotNull
    @XmlAttribute
    private Date modification;

    @Valid
    @XmlElement(name = "adapterConnectionConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<AdapterConnection, EnableState>> adapterConnectionConfigurations;
    @Valid
    @XmlElement(name = "adapterListenerConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<AdapterListener, ActivableState>> adapterListenerConfigurations;
    @Valid
    @XmlElement(name = "adapterNotificationConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<AdapterNotification, ActivableState>> adapterNotificationConfigurations;
    @Valid
    @XmlElement(name = "integrationServerPackageConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<IntegrationServerPackage, EnableState>> integrationServerPackageConfigurations;
    @Valid
    @XmlElement(name = "jmsAliasConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<JmsAlias, ConnectableState>> jmsAliasConfigurations;
    @Valid
    @XmlElement(name = "jmsTriggerConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<JmsTrigger, ActivableState>> jmsTriggerConfigurations;
    @Valid
    @XmlElement(name = "nativeTriggerConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<NativeTrigger, NativeTriggerState>> nativeTriggerConfigurations;
    @Valid
    @XmlElement(name = "portConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<Port, ActivableState>> portConfigurations;
    @Valid
    @XmlElement(name = "schedulerConfiguration")
    @XmlElementWrapper
    private final List<ComponentConfiguration<Scheduler, SchedulerState>> schedulerConfigurations;

    public Configuration(Builder builder) {
        id=builder.id;
        name = builder.name;
        packageName = builder.packageName;
        creation = builder.creation;
        modification = builder.modification;
        adapterConnectionConfigurations = builder.adapterConnectionConfigurations;
        adapterListenerConfigurations = builder.adapterListenerConfigurations;
        adapterNotificationConfigurations = builder.adapterNotificationConfigurations;
        integrationServerPackageConfigurations = builder.integrationServerPackageConfigurations;
        jmsAliasConfigurations = builder.jmsAliasConfigurations;
        jmsTriggerConfigurations = builder.jmsTriggerConfigurations;
        nativeTriggerConfigurations = builder.nativeTriggerConfigurations;
        portConfigurations = builder.portConfigurations;
        schedulerConfigurations = builder.schedulerConfigurations;
    }

    private Configuration() {
        id=null;
        name = null;
        packageName = null;
        creation = null;
        modification = null;
        adapterConnectionConfigurations = null;
        adapterListenerConfigurations = null;
        adapterNotificationConfigurations = null;
        integrationServerPackageConfigurations = null;
        jmsAliasConfigurations = null;
        jmsTriggerConfigurations = null;
        nativeTriggerConfigurations = null;
        portConfigurations = null;
        schedulerConfigurations = null;
    }

    @Override
    public List<ComponentConfiguration<AdapterConnection, EnableState>> getAdapterConnectionConfigurations() {
        return unmodifiableList(adapterConnectionConfigurations);
    }

    @Override
    public List<ComponentConfiguration<AdapterListener, ActivableState>> getAdapterListenerConfigurations() {
        return unmodifiableList(adapterListenerConfigurations);
    }

    @Override
    public List<ComponentConfiguration<AdapterNotification, ActivableState>> getAdapterNotificationConfigurations() {
        return unmodifiableList(adapterNotificationConfigurations);
    }

    @Override
    public List<ComponentConfiguration<IntegrationServerPackage, EnableState>> getIntegrationServerPackageConfigurations() {
        return unmodifiableList(integrationServerPackageConfigurations);
    }

    @Override
    public List<ComponentConfiguration<JmsAlias, ConnectableState>> getJmsAliasConfigurations() {
        return unmodifiableList(jmsAliasConfigurations);
    }

    @Override
    public List<ComponentConfiguration<JmsTrigger, ActivableState>> getJmsTriggerConfigurations() {
        return unmodifiableList(jmsTriggerConfigurations);
    }

    @Override
    public List<ComponentConfiguration<NativeTrigger, NativeTriggerState>> getNativeTriggerConfigurations() {
        return unmodifiableList(nativeTriggerConfigurations);
    }

    @Override
    public List<ComponentConfiguration<Port, ActivableState>> getPortConfigurations() {
        return unmodifiableList(portConfigurations);
    }

    @Override
    public List<ComponentConfiguration<Scheduler, SchedulerState>> getSchedulerConfigurations() {
        return unmodifiableList(schedulerConfigurations);
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.dispatchVisit(this);
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BuilderWithJsr303Validation<Builder, Configuration> {
        @NotNull
        @Pattern(regexp="\\w{1,32}")
        private String id;
        @NotNull
        private String name;
        @NotNull
        private String packageName;
        @NotNull
        private Date creation;
        @NotNull
        private Date modification;

        @Valid
        private List<ComponentConfiguration<AdapterConnection, EnableState>> adapterConnectionConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<AdapterListener, ActivableState>> adapterListenerConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<AdapterNotification, ActivableState>> adapterNotificationConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<IntegrationServerPackage, EnableState>> integrationServerPackageConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<JmsAlias, ConnectableState>> jmsAliasConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<JmsTrigger, ActivableState>> jmsTriggerConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<NativeTrigger, NativeTriggerState>> nativeTriggerConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<Port, ActivableState>> portConfigurations = newArrayList();
        @Valid
        private List<ComponentConfiguration<Scheduler, SchedulerState>> schedulerConfigurations = newArrayList();

        public Builder create(String packageName, String id, Date when) {
            this.id = id;
            this.name=id;
            this.packageName=packageName;
            this.creation = new Date(when.getTime());
            this.modification = new Date(when.getTime());
            return self();
        }

        public Builder in(String packageName) {
            this.packageName = checkNotNull(packageName);
            return self();
        }

        public Builder name(String name) {
            this.name = checkNotNull(name);
            return self();
        }

        public Builder touch(Date when) {
            modification = new Date(when.getTime());
            return self();
        }

        public Builder addAdapterConnection(ComponentConfiguration<AdapterConnection, EnableState> connection) {
            adapterConnectionConfigurations.add(connection);
            return self();
        }

        public Builder addAdapterConnections(Collection<ComponentConfiguration<AdapterConnection, EnableState>> connections) {
            adapterConnectionConfigurations.addAll(connections);
            return self();
        }

        public Builder addAdapterListener(ComponentConfiguration<AdapterListener, ActivableState> listener) {
            adapterListenerConfigurations.add(listener);
            return self();
        }

        public Builder addAdapterListeners(Collection<ComponentConfiguration<AdapterListener, ActivableState>> listeners) {
            adapterListenerConfigurations.addAll(listeners);
            return self();
        }

        public Builder addAdapterNotification(ComponentConfiguration<AdapterNotification, ActivableState> notification) {
            adapterNotificationConfigurations.add(notification);
            return self();
        }

        public Builder addAdapterNotifications(Collection<ComponentConfiguration<AdapterNotification, ActivableState>> notifications) {
            adapterNotificationConfigurations.addAll(notifications);
            return self();
        }

        public Builder addPackage(ComponentConfiguration<IntegrationServerPackage, EnableState> pkg) {
            integrationServerPackageConfigurations.add(pkg);
            return self();
        }

        public Builder addPackages(Collection<ComponentConfiguration<IntegrationServerPackage, EnableState>> pkgs) {
            integrationServerPackageConfigurations.addAll(pkgs);
            return self();
        }

        public Builder addJmsAliasConnection(ComponentConfiguration<JmsAlias, ConnectableState> alias) {
            jmsAliasConfigurations.add(alias);
            return self();
        }

        public Builder addJmsAliasConnections(Collection<ComponentConfiguration<JmsAlias, ConnectableState>> aliases) {
            jmsAliasConfigurations.addAll(aliases);
            return self();
        }

        public Builder addJmsTrigger(ComponentConfiguration<JmsTrigger, ActivableState> trigger) {
            jmsTriggerConfigurations.add(trigger);
            return self();
        }

        public Builder addJmsTriggers(Collection<ComponentConfiguration<JmsTrigger, ActivableState>> triggers) {
            jmsTriggerConfigurations.addAll(triggers);
            return self();
        }

        public Builder addNativeTrigger(ComponentConfiguration<NativeTrigger, NativeTriggerState> trigger) {
            nativeTriggerConfigurations.add(trigger);
            return self();
        }

        public Builder addNativeTriggers(Collection<ComponentConfiguration<NativeTrigger, NativeTriggerState>> triggers) {
            nativeTriggerConfigurations.addAll(triggers);
            return self();
        }

        public Builder addPort(ComponentConfiguration<Port, ActivableState> port) {
            portConfigurations.add(port);
            return self();
        }

        public Builder addPorts(Collection<ComponentConfiguration<Port, ActivableState>> ports) {
            ports.addAll(ports);
            return self();
        }

        public Builder addScheduler(ComponentConfiguration<Scheduler, SchedulerState> scheduler) {
            schedulerConfigurations.add(scheduler);
            return self();
        }

        public Builder addSchedulers(Collection<ComponentConfiguration<Scheduler, SchedulerState>> schedulers) {
            schedulers.addAll(schedulers);
            return self();
        }

        public Builder from(Configuration source) {
            name = source.getName();
            id = source.getId();
            packageName = source.getPackageName();
            creation = source.getCreation();
            modification = source.getModification();
            adapterConnectionConfigurations.addAll(source.getAdapterConnectionConfigurations());
            adapterListenerConfigurations.addAll(source.getAdapterListenerConfigurations());
            adapterNotificationConfigurations.addAll(source.getAdapterNotificationConfigurations());
            integrationServerPackageConfigurations.addAll(source.getIntegrationServerPackageConfigurations());
            jmsAliasConfigurations.addAll(source.getJmsAliasConfigurations());
            jmsTriggerConfigurations.addAll(source.getJmsTriggerConfigurations());
            nativeTriggerConfigurations.addAll(source.getNativeTriggerConfigurations());
            portConfigurations.addAll(source.getPortConfigurations());
            schedulerConfigurations.addAll(source.getSchedulerConfigurations());
            return self();
        }

        public Builder clear() {
            adapterConnectionConfigurations.clear();
            adapterListenerConfigurations.clear();
            adapterNotificationConfigurations.clear();
            integrationServerPackageConfigurations.clear();
            jmsAliasConfigurations.clear();
            jmsTriggerConfigurations.clear();
            nativeTriggerConfigurations.clear();
            portConfigurations.clear();
            schedulerConfigurations.clear();
            return self();
        }

        @Override
        protected Configuration buildObjectBeforeValidation() {
            return new Configuration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}

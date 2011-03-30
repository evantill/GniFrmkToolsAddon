package com.gni.frmk.tools.addon.configuration;

import com.gni.frmk.tools.addon.configuration.components.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
public class Configuration {

    @NotNull
    @XmlAttribute
    private final String name;
    @NotNull
    @XmlAttribute
    private final Date creation;
    @NotNull
    @XmlAttribute
    private Date modification;

    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<AdapterConnection> adapterConnections;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<AdapterListener> adapterListeners;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<AdapterNotification> adapterNotifications;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<IntegrationServerPackage> integrationServerPackages;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<JmsAlias> jmsAliases;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<JmsTrigger> jmsTriggers;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<NativeTrigger> nativeTriggers;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<Port> ports;
    @Valid
    @XmlElement
    @XmlElementWrapper
    private final List<Scheduler> schedulers;

    public Configuration(Builder builder) {
        name = builder.name;
        creation = builder.creation;
        modification = builder.modification;
        adapterConnections = builder.adapterConnections;
        adapterListeners = builder.adapterListeners;
        adapterNotifications = builder.adapterNotifications;
        integrationServerPackages = builder.integrationServerPackages;
        jmsAliases = builder.jmsAliases;
        jmsTriggers = builder.jmsTriggers;
        nativeTriggers = builder.nativeTriggers;
        ports = builder.ports;
        schedulers = builder.schedulers;
    }

    private Configuration() {
        name = null;
        creation = null;
        modification = null;
        adapterConnections = null;
        adapterListeners = null;
        adapterNotifications = null;
        integrationServerPackages = null;
        jmsAliases = null;
        jmsTriggers = null;
        nativeTriggers = null;
        ports = null;
        schedulers = null;
    }

    public List<AdapterConnection> getAdapterConnections() {
        return unmodifiableList(adapterConnections);
    }

    public List<AdapterListener> getAdapterListeners() {
        return unmodifiableList(adapterListeners);
    }

    public List<AdapterNotification> getAdapterNotifications() {
        return unmodifiableList(adapterNotifications);
    }

    public List<IntegrationServerPackage> getIntegrationServerPackages() {
        return unmodifiableList(integrationServerPackages);
    }

    public List<JmsAlias> getJmsAliases() {
        return unmodifiableList(jmsAliases);
    }

    public List<JmsTrigger> getJmsTriggers() {
        return unmodifiableList(jmsTriggers);
    }

    public List<NativeTrigger> getNativeTriggers() {
        return unmodifiableList(nativeTriggers);
    }

    public List<Port> getPorts() {
        return unmodifiableList(ports);
    }

    public List<Scheduler> getSchedulers() {
        return unmodifiableList(schedulers);
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BuilderWithJsr303Validation<Builder,Configuration> {
        private String name;
        private Date creation;
        private Date modification;
        private List<AdapterConnection> adapterConnections = newArrayList();
        private List<AdapterListener> adapterListeners = newArrayList();
        private List<AdapterNotification> adapterNotifications = newArrayList();
        private List<IntegrationServerPackage> integrationServerPackages = newArrayList();
        private List<JmsAlias> jmsAliases = newArrayList();
        private List<JmsTrigger> jmsTriggers = newArrayList();
        private List<NativeTrigger> nativeTriggers = newArrayList();
        private List<Port> ports = newArrayList();
        private List<Scheduler> schedulers = newArrayList();

        public Builder create(String name, Date when) {
            this.name = name;
            this.creation = new Date(when.getTime());
            this.modification = new Date(when.getTime());
            return self();
        }

        public Builder touch(Date when) {
            modification = new Date(when.getTime());
            return self();
        }

        public Builder addAdapterConnection(AdapterConnection connection) {
            adapterConnections.add(connection);
           return self();
        }

        public Builder addAdapterConnections(Collection<AdapterConnection> connections) {
            adapterConnections.addAll(connections);
            return self();
        }

        public Builder addAdapterListener(AdapterListener listener) {
            adapterListeners.add(listener);
           return self();
        }

        public Builder addAdapterListeners(Collection<AdapterListener> listeners) {
            adapterListeners.addAll(listeners);
           return self();
        }

        public Builder addAdapterNotification(AdapterNotification notification) {
            adapterNotifications.add(notification);
           return self();
        }

        public Builder addAdapterNotifications(Collection<AdapterNotification> notifications) {
            adapterNotifications.addAll(notifications);
           return self();
        }

        public Builder addPackage(IntegrationServerPackage pkg) {
            integrationServerPackages.add(pkg);
           return self();
        }

        public Builder addPackages(Collection<IntegrationServerPackage> pkgs) {
            integrationServerPackages.addAll(pkgs);
            return self();
        }

        public Builder addJmsAliasConnection(JmsAlias alias) {
            jmsAliases.add(alias);
            return self();
        }

        public Builder addJmsAliasConnections(Collection<JmsAlias> aliases) {
            jmsAliases.addAll(aliases);
           return self();
        }

        public Builder addJmsTrigger(JmsTrigger trigger) {
            jmsTriggers.add(trigger);
            return self();
        }

        public Builder addJmsTriggers(Collection<JmsTrigger> triggers) {
            jmsTriggers.addAll(triggers);
            return self();
        }

        public Builder addNativeTrigger(NativeTrigger trigger) {
            nativeTriggers.add(trigger);
           return self();
        }

        public Builder addNativeTriggers(Collection<NativeTrigger> triggers) {
            nativeTriggers.addAll(triggers);
            return self();
        }

        public Builder addPort(Port port) {
            ports.add(port);
           return self();
        }

        public Builder addPorts(Collection<Port> ports) {
            ports.addAll(ports);
            return self();
        }

        public Builder addScheduler(Scheduler scheduler) {
            schedulers.add(scheduler);
           return self();
        }

        public Builder addSchedulers(Collection<Scheduler> schedulers) {
            schedulers.addAll(schedulers);
            return self();
        }

        public Builder from(Configuration source) {
            name = source.getName();
            creation = source.getCreation();
            modification = source.getModification();
            adapterConnections.addAll(source.getAdapterConnections());
            adapterListeners.addAll(source.getAdapterListeners());
            adapterNotifications.addAll(source.getAdapterNotifications());
            integrationServerPackages.addAll(source.getIntegrationServerPackages());
            jmsAliases.addAll(source.getJmsAliases());
            jmsTriggers.addAll(source.getJmsTriggers());
            nativeTriggers.addAll(source.getNativeTriggers());
            ports.addAll(source.getPorts());
            schedulers.addAll(source.getSchedulers());
           return self();
        }

        public Builder clear() {
            adapterConnections.clear();
            adapterListeners.clear();
            adapterNotifications.clear();
            integrationServerPackages.clear();
            jmsAliases.clear();
            jmsTriggers.clear();
            nativeTriggers.clear();
            ports.clear();
            schedulers.clear();
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

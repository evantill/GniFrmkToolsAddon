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

    public static class Builder implements BuilderWithValidation<Configuration> {
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
            return this;
        }

        public Builder touch(Date when) {
            modification = new Date(when.getTime());
            return this;
        }

        public Builder addAdapterConnection(AdapterConnection connection) {
            adapterConnections.add(connection);
            return this;
        }

        public Builder addAdapterConnections(Collection<AdapterConnection> connections) {
            adapterConnections.addAll(connections);
            return this;
        }

        public Builder addAdapterListener(AdapterListener listener) {
            adapterListeners.add(listener);
            return this;
        }

        public Builder addAdapterListeners(Collection<AdapterListener> listeners) {
            adapterListeners.addAll(listeners);
            return this;
        }

        public Builder addAdapterNotification(AdapterNotification notification) {
            adapterNotifications.add(notification);
            return this;
        }

        public Builder addAdapterNotifications(Collection<AdapterNotification> notifications) {
            adapterNotifications.addAll(notifications);
            return this;
        }

        public Builder addPackage(IntegrationServerPackage pkg) {
            integrationServerPackages.add(pkg);
            return this;
        }

        public Builder addPackages(Collection<IntegrationServerPackage> pkgs) {
            integrationServerPackages.addAll(pkgs);
            return this;
        }

        public Builder addJmsAliasConnection(JmsAlias alias) {
            jmsAliases.add(alias);
            return this;
        }

        public Builder addJmsAliasConnections(Collection<JmsAlias> aliases) {
            jmsAliases.addAll(aliases);
            return this;
        }

        public Builder addJmsTrigger(JmsTrigger trigger) {
            jmsTriggers.add(trigger);
            return this;
        }

        public Builder addJmsTriggers(Collection<JmsTrigger> triggers) {
            jmsTriggers.addAll(triggers);
            return this;
        }

        public Builder addNativeTrigger(NativeTrigger trigger) {
            nativeTriggers.add(trigger);
            return this;
        }

        public Builder addNativeTriggers(Collection<NativeTrigger> triggers) {
            nativeTriggers.addAll(triggers);
            return this;
        }

        public Builder addPort(Port port) {
            ports.add(port);
            return this;
        }

        public Builder addPorts(Collection<Port> ports) {
            ports.addAll(ports);
            return this;
        }

        public Builder addScheduler(Scheduler scheduler) {
            schedulers.add(scheduler);
            return this;
        }

        public Builder addSchedulers(Collection<Scheduler> schedulers) {
            schedulers.addAll(schedulers);
            return this;
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
            return this;
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
            return this;
        }

        @Override
        public Configuration build() throws BuildException {
            return new Configuration(this);
        }

        @Override
        public Configuration buildAndValidate() throws BuildException, ValidationException {
            Configuration cnf = build();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Configuration>> constraintViolations = validator.validate(cnf);
            if (constraintViolations.size() > 0) {
                //TODO implementer gestion exception
                throw new RuntimeException("todo");
                //throw new ValidationException(constraintViolations);
            }
            return cnf;
        }

        @Override
        public void validate() throws ValidationException {
            //TODO validate
        }

    }

}

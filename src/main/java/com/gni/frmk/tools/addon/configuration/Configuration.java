package com.gni.frmk.tools.addon.configuration;

import com.gni.frmk.tools.addon.configuration.components.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
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
public class Configuration {

    @NotNull
    private final String name;
    @NotNull
    private final Date creation;
    @NotNull
    private Date modification;

    @Valid
    private final List<AdapterConnection> adapterConnections;
    @Valid
    private final List<AdapterListener> adapterListeners;
    @Valid
    private final List<AdapterNotification> adapterNotifications;
    @Valid
    private final List<IntegrationServerPackage> integrationServerPackages;
    @Valid
    private final List<JmsAlias> jmsAliases;
    @Valid
    private final List<JmsTrigger> jmsTriggers;
    @Valid
    private final List<NativeTrigger> nativeTriggers;
    @Valid
    private final List<Port> ports;
    @Valid
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

        public Builder touch() {
            modification = new Date();
            return this;
        }

        public Builder addAdapterConnection(AdapterConnection connection) {
            adapterConnections.add(connection);
            return this;
        }

        public Builder addAdapterListener(AdapterListener listener) {
            adapterListeners.add(listener);
            return this;
        }

        public Builder addAdapterNotification(AdapterNotification notification) {
            adapterNotifications.add(notification);
            return this;
        }

        public Builder addPackage(IntegrationServerPackage pkg) {
            integrationServerPackages.add(pkg);
            return this;
        }

        public Builder addJmsAliasConnection(JmsAlias alias) {
            jmsAliases.add(alias);
            return this;
        }

        public Builder addJmsTrigger(JmsTrigger trigger) {
            jmsTriggers.add(trigger);
            return this;
        }

        public Builder addNativeTrigger(NativeTrigger trigger) {
            nativeTriggers.add(trigger);
            return this;
        }

        public Builder addPort(Port port) {
            ports.add(port);
            return this;
        }

        public Builder addScheduler(Scheduler scheduler) {
            schedulers.add(scheduler);
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

        public void clear() {
            adapterConnections.clear();
            adapterListeners.clear();
            adapterNotifications.clear();
            integrationServerPackages.clear();
            jmsAliases.clear();
            jmsTriggers.clear();
            nativeTriggers.clear();
            ports.clear();
            schedulers.clear();
        }
    }

}

package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class Scheduler extends AbstractComponent {

    public static final ComponentType TYPE = ComponentType.SCHEDULER;

    private static final String DESCRIPTION_KEY = "description";
    private static final String SERVICE_KEY = "service";
    private static final String SCHEDULER_NAME_KEY = "schedulerName";
    private static final String SCHEDULER_TYPE_KEY = "schedulerType";

    private final EnableComponentState state;

    private Scheduler(Builder<?, ?> builder) {
        super(builder);
        state = builder.getState();
    }

    /**
     * empty constructor for jaxb.
     */
    private Scheduler() {
        state = null;
    }

    public EnableComponentState getState() {
        return state;
    }

    public String getDescription() {
        return findRequiredDetail(DESCRIPTION_KEY).getValue();
    }

    public void setDescription(String description) {
        addDetail(new ComponentDetail(DESCRIPTION_KEY, description));
    }

    @XmlTransient
    public String getService() {
        return findRequiredDetail(SERVICE_KEY).getValue();
    }

    public void setService(String service) {
        addDetail(new ComponentDetail(SERVICE_KEY, service));
    }

    @XmlTransient
    public String getSchedulerName() {
        return findRequiredDetail(SCHEDULER_NAME_KEY).getValue();
    }

    public void setSchedulerName(String name) {
        addDetail(new ComponentDetail(SCHEDULER_NAME_KEY, name));
    }

    @XmlTransient
    public String getSchedulerType() {
        return findRequiredDetail(SCHEDULER_TYPE_KEY).getValue();
    }

    public void setSchedulerType(String type) {
        addDetail(new ComponentDetail(SCHEDULER_TYPE_KEY, type));
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static SchedulerBuilder builder() {
        return new SchedulerBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends AbstractComponent.Builder<S, T> {

        private String description;
        private String service;
        private String schedulerName;
        private String schedulerType;

        public T description(String value) {
            description = checkNotNull(value, "description required");
            addDetail(DESCRIPTION_KEY, description);
            return self();
        }

        public T service(String value) {
            service = checkNotNull(value, "service required");
            addDetail(SERVICE_KEY, service);
            return self();
        }

        public T schedulerName(String value) {
            schedulerName = checkNotNull(value, "schedulerName required");
            addDetail(SCHEDULER_NAME_KEY, schedulerName);
            return self();
        }

        public T schedulerType(String value) {
            schedulerType = checkNotNull(value, "schedulerType required");
            addDetail(SCHEDULER_TYPE_KEY, schedulerType);
            return self();
        }

        @Override
        public T check() throws CheckException {
            super.check();
            try {
                checkNotNull(schedulerName, "schedulerName required");
                checkNotNull(schedulerType, "schedulerType required");
                checkNotNull(service, "service required");
                return self();
            } catch (NullPointerException npex) {
                throw new CheckException(npex);
            }
        }

        public Builder() {
            type(TYPE);
        }

        @Override
        public Scheduler build() {
            return new Scheduler(this);
        }

    }

    public static class SchedulerBuilder extends Builder<EnableComponentState, SchedulerBuilder> {
        @Override
        protected SchedulerBuilder self() {
            return this;
        }
    }

}

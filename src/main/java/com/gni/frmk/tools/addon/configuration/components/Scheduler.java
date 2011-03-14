package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Scheduler.SchedulerState;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

import static com.gni.frmk.tools.addon.configuration.components.Component.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
public class Scheduler extends PackageAware<StringId, SchedulerState> {

    @NotNull
    private final String oid;
    @NotNull
    private final String schedulerType;
    @NotNull
    private final String name;
    @NotNull
    private final String service;

    public Scheduler(Builder<?, ?, StringId, SchedulerState> builder) {
        super(builder);
        oid = builder.oid;
        schedulerType = builder.schedulerType;
        name = builder.name;
        service = builder.service;
    }

    public String getOid() {
        return oid;
    }

    public String getSchedulerType() {
        return schedulerType;
    }

    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static class SchedulerState extends EnableState {
        public static enum SchedulerStatus {
            UNEXPIRED, EXPIRED
        }

        private final SchedulerStatus scheduled;

        public SchedulerState(EnableStatus enabled, SchedulerStatus scheduled) {
            super(enabled);
            this.scheduled = scheduled;
        }

        public SchedulerStatus getScheduled() {
            return scheduled;
        }

        @Override
        public ComponentStateStatus getComponentStatus() {
            ComponentStateStatus enableStatus = super.getComponentStatus();
            switch (scheduled) {
                case UNEXPIRED:
                    return enableStatus.composeWith(ON);
                case EXPIRED:
                    return enableStatus.composeWith(OFF);
                default:
                    return enableStatus.composeWith(UNKNOWN);
            }
        }


    }

    public static SchedulerBuilder builder() {
        return new SchedulerBuilder();
    }

    public static class SchedulerBuilder extends Builder<SchedulerBuilder, Scheduler, StringId, SchedulerState> {

        public SchedulerBuilder() {
            defineType(ComponentType.SCHEDULER);
        }

        @Override
        public SchedulerBuilder self() {
            return this;
        }

        @Override
        public Builder<SchedulerBuilder, Scheduler, StringId, SchedulerState> oid(String value) {
            super.oid(value);
            defineId(new StringId(oid));
            return self();
        }

        @Override
        public Scheduler build() {
            return new Scheduler(this);
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends PackageAware<I, S>, I extends StringId, S extends SchedulerState>
            extends PackageAware.Builder<T, B, I, S> {

        protected String oid;
        protected String schedulerType;
        protected String name;
        protected String service;

        public Builder<T, B, I, S> oid(String value) {
            oid = value;
            return self();
        }

        public Builder<T, B, I, S> schedulerType(String value) {
            schedulerType = value;
            return self();
        }

        public Builder<T, B, I, S> name(String value) {
            name = value;
            return self();
        }

        public Builder<T, B, I, S> service(String value) {
            service = value;
            return self();
        }
    }
}
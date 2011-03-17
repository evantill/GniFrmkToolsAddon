package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Scheduler.SchedulerState;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

import static com.gni.frmk.tools.addon.configuration.components.ComponentState.ComponentStateStatus.*;
import static com.google.common.base.Preconditions.checkNotNull;

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

    private final String description;

    public Scheduler(SchedulerBuilder builder) {
        super(builder);
        oid = builder.oid;
        schedulerType = builder.schedulerType;
        name = builder.name;
        service = builder.service;
        description = builder.description;
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

    public String getDescription() {
        return description;
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

    public static class SchedulerBuilder extends PackageAware.Builder<SchedulerBuilder, Scheduler, StringId, SchedulerState> {

        private String oid;
        private String schedulerType;
        private String name;
        private String service;
        private String description;

        public SchedulerBuilder() {
            defineType(ComponentType.SCHEDULER);
        }

        public SchedulerBuilder schedulerType(String value) {
            schedulerType = checkNotNull(value);
            return self();
        }

        public SchedulerBuilder name(String value) {
            name = checkNotNull(value);
            return self();
        }

        public SchedulerBuilder oid(String value) {
            oid = checkNotNull(value);
            defineId(new StringId(oid));
            return self();
        }

        public SchedulerBuilder service(String value) {
            service = checkNotNull(value);
            return self();
        }

        public SchedulerBuilder description(String value) {
            description = value;
            return self();
        }

        @Override
        public SchedulerBuilder self() {
            return this;
        }

        @Override
        public Scheduler build() {
            return new Scheduler(this);
        }
    }
}
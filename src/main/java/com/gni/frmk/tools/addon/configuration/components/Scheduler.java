package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
@XmlRootElement
public class Scheduler extends PackageAware<StringId, SchedulerState> {

    @NotNull
    @XmlElement
    private final String oid;
    @NotNull
    @XmlElement
    private final String schedulerType;
    @NotNull
    @XmlElement
    private final String name;
    @NotNull
    @XmlElement
    private final String service;

    @XmlElement
    private final String description;

    public Scheduler(SchedulerBuilder builder) {
        super(builder);
        oid = builder.oid;
        schedulerType = builder.schedulerType;
        name = builder.name;
        service = builder.service;
        description = builder.description;
    }

    private Scheduler() {
        super();
        oid=null;
        schedulerType = null;
        name = null;
        service = null;
        description = null;
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
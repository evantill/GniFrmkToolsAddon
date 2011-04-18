package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.ImmutableScheduler.MutableScheduler;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
@XmlRootElement
public class ImmutableScheduler
        extends ImmutablePackageAware<ImmutableScheduler, MutableScheduler, StringId, SchedulerState>
        implements ReadableScheduler {

    private final String oid;
    private final String schedulerType;
    private final String name;
    private final String service;
    private final String description;

    public ImmutableScheduler(ReadableScheduler source) {
        super(source);
        oid = source.getOid();
        schedulerType = source.getSchedulerType();
        name = source.getName();
        service = source.getService();
        description = source.getDescription();
    }

    @Override
    public ImmutableScheduler toImmutable() {
        return this;
    }

    @Override
    public MutableScheduler toMutable() {
        return new MutableScheduler(this);
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
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    public static class MutableScheduler
            extends MutablePackageAware<ImmutableScheduler, MutableScheduler, StringId, SchedulerState>
            implements WritableScheduler {

        private String oid;
        private String schedulerType;
        private String name;
        private String service;
        private String description;

        public MutableScheduler() {
            setType(ComponentType.SCHEDULER);
        }

        public MutableScheduler(ReadableScheduler source) {
            super(source);
            setOid(source.getOid());
            setSchedulerType(source.getSchedulerType());
            setName(source.getName());
            setService(source.getService());
            setDescription(source.getDescription());
            setType(ComponentType.SCHEDULER);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitComponent(toImmutable());
        }

        @Override
        public ImmutableScheduler toImmutable() {
            return new ImmutableScheduler(this);
        }

        @Override
        public MutableScheduler toMutable() {
            return new MutableScheduler(this);
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            setComponentId(new StringId(oid));
            this.oid = oid;
        }

        public String getSchedulerType() {
            return schedulerType;
        }

        public void setSchedulerType(String schedulerType) {
            this.schedulerType = schedulerType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
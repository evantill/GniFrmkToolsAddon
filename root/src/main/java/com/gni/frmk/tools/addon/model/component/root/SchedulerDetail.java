package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentDetail;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 17:02
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "schedulerType",
        "name",
        "service",
        "description"
})
public class SchedulerDetail
        extends BaseComponentDetail<SchedulerDetail> {

    private String schedulerType;
    private String name;
    private String service;
    private String description;

    private SchedulerDetail() {
    }

    public SchedulerDetail(Builder builder) {
        this.schedulerType = builder.schedulerType;
        this.name = builder.name;
        this.service = builder.service;
        this.description = builder.description;
    }

    @Override
    public int compareTo(SchedulerDetail o) {
        return ComparisonChain.start()
                              .compare(schedulerType, o.schedulerType)
                              .compare(name, o.name)
                              .compare(service, o.service)
                              .compare(description, o.description)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchedulerDetail that = (SchedulerDetail) o;

        return Objects.equal(schedulerType, that.schedulerType)
               && Objects.equal(name, that.name)
               && Objects.equal(service, that.service)
               && Objects.equal(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, schedulerType, service, description);
    }

    @XmlElement
    public String getSchedulerType() {
        return schedulerType;
    }

    private void setSchedulerType(String schedulerType) {
        this.schedulerType = schedulerType;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getService() {
        return service;
    }

    private void setService(String service) {
        this.service = service;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponentDetail.Builder<Builder, SchedulerDetail> {
        private String schedulerType;
        private String name;
        private String service;
        private String description;

        public Builder schedulerType(String value) {
            schedulerType = checkNotNull(value);
            return this;
        }

        public Builder name(String value) {
            name = checkNotNull(value);
            return this;
        }

        public Builder service(String value) {
            service = checkNotNull(value);
            return this;
        }

        public Builder description(String value) {
            description = checkNotNull(value);
            return this;
        }

        @Override
        public SchedulerDetail build() {
            return new SchedulerDetail(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(schedulerType);
            checkNotNull(name);
            checkNotNull(service);
            checkNotNull(description);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}

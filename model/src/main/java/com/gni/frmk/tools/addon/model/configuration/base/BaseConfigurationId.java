package com.gni.frmk.tools.addon.model.configuration.base;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 12:19
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {"id",
                      "packageName"})
public class BaseConfigurationId
        implements ConfigurationId<BaseConfigurationId> {
    private String id;
    private String packageName;

    private BaseConfigurationId() {
    }

    private BaseConfigurationId(String id, String packageName) {
        this.id = id;
        this.packageName = packageName;
    }

    public BaseConfigurationId(Builder builder) {
        id = builder.id;
        packageName = builder.packageName;
    }

    @NotNull
    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @XmlElement
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseConfigurationId that = (BaseConfigurationId) o;
        return Objects.equal(getId(), that.getId())
               && Objects.equal(getPackageName(), that.getPackageName());
    }

    @Override
    public int compareTo(BaseConfigurationId that) {
        return ComparisonChain.start()
                              .compare(packageName, that.packageName)
                              .compare(id, that.id)
                              .result();
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static class Builder
            implements BuilderWithValidation<Builder, BaseConfigurationId> {
        private String id;
        private String packageName;

        public Builder id(String value) {
            id = checkNotNull(value);
            return this;
        }

        public Builder packageName(String value) {
            packageName = checkNotNull(value);
            return this;
        }

        @Override
        public BaseConfigurationId build() {
            return new BaseConfigurationId(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(id);
            checkNotNull(packageName);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
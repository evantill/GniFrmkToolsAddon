package com.gni.frmk.tools.addon.model.configuration.base;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationInfo;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 11:25
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "name",
        "creation",
        "modification"
})
public class BaseConfigurationInformation implements ConfigurationInfo<BaseConfigurationInformation> {
    private String name;
    private Date creation;
    private Date modification;

    private BaseConfigurationInformation() {
    }

    private BaseConfigurationInformation(Builder builder) {
        name = builder.name;
        creation = builder.creation;
        modification = builder.modification;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Date getCreation() {
        return creation;
    }

    private void setCreation(Date creation) {
        this.creation = creation;
    }

    @XmlElement
    public Date getModification() {
        return modification;
    }

    private void setModification(Date modification) {
        this.modification = modification;
    }

    @Override
    public int compareTo(BaseConfigurationInformation o) {
        return ComparisonChain.start()
                              .compare(name, o.name)
                              .compare(creation, o.creation)
                              .compare(modification, o.modification)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseConfigurationInformation that = (BaseConfigurationInformation) o;

        return Objects.equal(name, that.name)
               && Objects.equal(creation, that.creation)
               && Objects.equal(modification, that.modification);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, creation, modification);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static BaseConfigurationInformation touch(BaseConfigurationInformation info, Date when) {
        return new Builder(info).modification(when).validate().build();
    }

    @XmlTransient
    public static class Builder
            implements BuilderWithValidation<Builder, BaseConfigurationInformation> {

        private String name;
        private Date creation;
        private Date modification;

        public Builder() {
        }

        public Builder(BaseConfigurationInformation src) {
            name(src.name)
                    .creation(src.creation)
                    .modification(src.modification);
        }

        public Builder creation(Date value) {
            creation = new Date(checkNotNull(value).getTime());
            return this;
        }

        public Builder modification(Date value) {
            modification = new Date(checkNotNull(value).getTime());
            return this;
        }

        public Builder name(String value) {
            name = checkNotNull(value);
            return this;
        }

        @Override
        public BaseConfigurationInformation build() {
            return new BaseConfigurationInformation(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(name);
            checkNotNull(creation);
            checkNotNull(modification);
            Preconditions.checkArgument(creation.compareTo(modification) >= 0);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}

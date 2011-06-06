package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentId;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:26
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "adapterType",
        "name"
})
public class AdapterId
        extends BaseComponentId<AdapterId> {

    private String name;
    private String adapterType;

    private AdapterId() {
    }

    public AdapterId(Builder builder) {
        this.name = builder.name;
        this.adapterType = builder.adapterType;
    }

    @Override
    public int compareTo(AdapterId o) {
        return ComparisonChain.start()
                              .compare(adapterType, o.adapterType)
                              .compare(name, o.name)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdapterId other = (AdapterId) o;

        return Objects.equal(adapterType, other.adapterType)
               && Objects.equal(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(adapterType,name);
    }

    @XmlElement
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getAdapterType() {
        return adapterType;
    }

    private void setAdapterType(String adapterType) {
        this.adapterType = adapterType;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static AdapterId newInstance(String name, String adapterType) {
        return builder().adapterType(adapterType).name(name).validate().build();
    }

    public static AdapterId build(String adapterType, String name) {
        return builder().adapterType(adapterType).name(name).validate().build();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentId.Builder<Builder, AdapterId> {
        public String name;
        public String adapterType;

        public Builder name(String value) {
            name = checkNotNull(value);
            return this;
        }

        public Builder adapterType(String value) {
            adapterType = checkNotNull(value);
            return this;
        }

        @Override
        public AdapterId build() {
            return new AdapterId(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(name);
            checkNotNull(adapterType);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}

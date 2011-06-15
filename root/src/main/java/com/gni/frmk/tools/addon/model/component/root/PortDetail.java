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
@XmlType(propOrder = {"primary"})
public class PortDetail extends BaseComponentDetail<PortDetail> {
    private boolean primary;

    private PortDetail() {
    }

    public PortDetail(Builder builder) {
        super(builder);
        primary = builder.primary;
    }

    @Override
    public int compareTo(PortDetail o) {
        return ComparisonChain.start()
                              .compare(primary, o.primary)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortDetail that = (PortDetail) o;

        return Objects.equal(primary, that.primary);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(primary);
    }

    @XmlElement
    public boolean isPrimary() {
        return primary;
    }

    private void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static PortDetail newInstance(boolean primary) {
        return builder().primary(primary).validate().build();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponentDetail.Builder<Builder, PortDetail> {

        private Boolean primary;

        public Builder primary(boolean value) {
            primary = checkNotNull(value);
            return this;
        }

        @Override
        public PortDetail build() {
            return new PortDetail(this);
        }

        @Override
        public Builder validate() {
            checkNotNull(primary);
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}

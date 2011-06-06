package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.gni.frmk.tools.addon.model.component.EnableStatus.UNKNOWN;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "enabled"
})
public final class EnableState extends BaseComponentState<EnableState> {

    private EnableStatus enabled = UNKNOWN;

    protected EnableState() {
        super();
    }

    public EnableState(Builder builder) {
        super(builder);
        enabled = builder.enabled;
    }

    @XmlElement
    public EnableStatus getEnabled() {
        return enabled;
    }

    private void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    public int compareTo(EnableState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getEnabled(), other.getEnabled())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EnableState that = (EnableState) o;

        return Objects.equal(exist(), that.exist())
               && Objects.equal(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exist(), enabled);
    }

    @Override
    public boolean unknown() {
        return enabled == UNKNOWN;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static EnableState build(EnableStatus enabled) {
        return builder().enable(enabled).validate().build();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentState.Builder<Builder, EnableState> {
        private EnableStatus enabled;

        public Builder enable(EnableStatus enabled) {
            this.enabled = checkNotNull(enabled);
            //if using switch compiler generate a $1 class and this is a problem for MoxY
            if (enabled == EnableStatus.UNKNOWN) {
                exist(false);
            } else {
                exist(true);
            }
            return this;
        }

        @Override
        public EnableState build() {
            return new EnableState(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder validate() {
            checkNotNull(enabled);
            return super.validate();
        }
    }

}
